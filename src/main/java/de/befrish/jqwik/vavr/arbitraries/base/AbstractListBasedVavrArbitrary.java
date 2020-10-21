package de.befrish.jqwik.vavr.arbitraries.base;

import io.vavr.collection.Traversable;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.ExhaustiveGenerator;
import net.jqwik.api.RandomGenerator;
import net.jqwik.engine.properties.arbitraries.exhaustive.ExhaustiveGenerators;
import net.jqwik.engine.properties.shrinking.ShrinkableList;

import java.util.Optional;

public abstract class AbstractListBasedVavrArbitrary<T, U extends Traversable<T>> extends AbstractCollectionBasedArbitrary<T, U> {

	public AbstractListBasedVavrArbitrary(final Arbitrary<T> elementArbitrary, final boolean elementsUnique) {
		super(elementArbitrary, elementsUnique);
	}

	protected abstract U convertJavaListToVavrCollection(java.util.List<T> javaList);

	@Override
	protected Iterable<T> toIterable(final U streamable) {
		return streamable;
	}

	@Override
	public RandomGenerator<U> generator(final int genSize) {
		return createListGenerator(genSize).map(this::convertJavaListToVavrCollection);
	}

	@Override
	public Optional<ExhaustiveGenerator<U>> exhaustive(final long maxNumberOfSamples) {
		return ExhaustiveGenerators.list(this.elementArbitrary, this.minSize, this.maxSize, maxNumberOfSamples)
				.map(exhaustiveGenerator -> exhaustiveGenerator.map(this::convertJavaListToVavrCollection));
	}

	@Override
	public EdgeCases<U> edgeCases() {
		return edgeCases((elements, minSize1) -> new ShrinkableList<>(elements, minSize1, this.maxSize))
				.map(this::convertJavaListToVavrCollection);
	}

}
