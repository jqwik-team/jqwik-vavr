package de.befrish.jqwik.vavr.arbitraries.impl;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.RandomGenerator;
import net.jqwik.api.Shrinkable;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.engine.properties.arbitraries.AbstractArbitraryBase;
import net.jqwik.engine.properties.arbitraries.EdgeCasesSupport;
import net.jqwik.engine.properties.arbitraries.randomized.RandomGenerators;
import net.jqwik.engine.properties.shrinking.ShrinkableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import static net.jqwik.engine.properties.arbitraries.ArbitrariesSupport.maxNumberOfElements;

abstract class MultivalueArbitraryBase<T, U> extends AbstractArbitraryBase implements StreamableArbitrary<T, U> {

	protected Arbitrary<T> elementArbitrary;
	protected int minSize = 0;
	protected int maxSize = RandomGenerators.DEFAULT_COLLECTION_SIZE;
	private final boolean elementsUnique;

	protected MultivalueArbitraryBase(final Arbitrary<T> elementArbitrary, final boolean elementsUnique) {
		this.elementArbitrary = elementArbitrary;
		this.elementsUnique = elementsUnique;
		if (elementsUnique) {
			this.maxSize = maxNumberOfElements(elementArbitrary, RandomGenerators.DEFAULT_COLLECTION_SIZE);
		}
	}

	@Override
	public StreamableArbitrary<T, U> ofMinSize(final int minSize) {
		final MultivalueArbitraryBase<T, U> clone = typedClone();
		clone.minSize = minSize;
		return clone;
	}

	@Override
	public StreamableArbitrary<T, U> ofMaxSize(final int maxSize) {
		final MultivalueArbitraryBase<T, U> clone = typedClone();
		clone.maxSize = maxSize;
		return clone;
	}

	@Override
	public <R> Arbitrary<R> reduce(final R initial, final BiFunction<R, T, R> accumulator) {
		return this.map(streamable -> {
			// Couldn't find a way to use Stream.reduce since it requires a combinator
			@SuppressWarnings("unchecked") final R[] result = (R[]) new Object[]{initial};
			final Iterable<T> iterable = toIterable(streamable);
			for (final T each : iterable) {
				result[0] = accumulator.apply(result[0], each);
			}
			return result[0];
		});
	}

	protected abstract Iterable<T> toIterable(U streamable);

	protected RandomGenerator<List<T>> createListGenerator(final int genSize) {
		final RandomGenerator<T> elementGenerator = elementGenerator(this.elementArbitrary, genSize);
		final EdgeCases<List<T>> edgeCases = edgeCases((elements, minSize1) -> new ShrinkableList<>(elements, minSize1, this.maxSize));
		return RandomGenerators
				   .list(elementGenerator, this.minSize, this.maxSize, cutoffSize(genSize))
				   .withEdgeCases(genSize, edgeCases);
	}

	protected int cutoffSize(final int genSize) {
		return RandomGenerators.defaultCutoffSize(this.minSize, this.maxSize, genSize);
	}

	protected RandomGenerator<T> elementGenerator(final Arbitrary<T> elementArbitrary, final int genSize) {
		return elementArbitrary.generator(genSize);
	}

	protected <C extends Collection<?>> EdgeCases<C> edgeCases(final BiFunction<List<Shrinkable<T>>, Integer, Shrinkable<C>> shrinkableCreator) {
		final EdgeCases<C> emptyListEdgeCase = (this.minSize == 0) ? emptyListEdgeCase(shrinkableCreator) : EdgeCases.none();
		final EdgeCases<C> singleElementEdgeCases = (this.minSize <= 1 && this.maxSize >= 1) ? fixedSizeEdgeCases(1, shrinkableCreator) : EdgeCases.none();
		final EdgeCases<C> fixedSizeEdgeCases = generateFixedSizeEdgeCases() ? fixedSizeEdgeCases(this.minSize, shrinkableCreator) : EdgeCases.none();
		return EdgeCasesSupport.concat(Arrays.asList(emptyListEdgeCase, singleElementEdgeCases, fixedSizeEdgeCases));
	}

	private boolean generateFixedSizeEdgeCases() {
		if (this.elementsUnique) {
			return false;
		}
		return this.minSize == this.maxSize && this.minSize > 1;
	}

	private <C extends Collection<?>> EdgeCases<C> fixedSizeEdgeCases(
		final int fixedSize,
		final BiFunction<List<Shrinkable<T>>, Integer, Shrinkable<C>> shrinkableCreator
	) {
		return this.elementArbitrary
				   .edgeCases()
				   .mapShrinkable((Shrinkable<T> shrinkableT) -> {
					   final List<Shrinkable<T>> elements = new ArrayList<>(Collections.nCopies(fixedSize, shrinkableT));
					   return shrinkableCreator.apply(elements, this.minSize);
				   });
	}

	private <C extends Collection<?>> EdgeCases<C> emptyListEdgeCase(final BiFunction<List<Shrinkable<T>>, Integer, Shrinkable<C>> shrinkableCreator) {
		return EdgeCases.fromSupplier(
			() -> shrinkableCreator.apply(Collections.emptyList(), this.minSize)
		);
	}

}
