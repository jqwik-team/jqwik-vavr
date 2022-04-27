package net.jqwik.vavr.arbitraries.base;

import io.vavr.collection.Traversable;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.ExhaustiveGenerator;
import net.jqwik.api.RandomGenerator;
import net.jqwik.engine.properties.arbitraries.EdgeCasesSupport;
import net.jqwik.engine.properties.arbitraries.exhaustive.ExhaustiveGenerators;
import net.jqwik.engine.properties.shrinking.ShrinkableList;

import java.util.Optional;

public abstract class ListBasedVavrArbitrary<T, U extends Traversable<T>> extends MultivalueArbitrary<T, U> {

	public ListBasedVavrArbitrary(final Arbitrary<T> elementArbitrary) {
		super(elementArbitrary);
	}

	protected abstract U convertJavaListToVavrCollection(java.util.List<T> javaList);

	@Override
	protected Iterable<T> toIterable(final U streamable) {
		return streamable;
	}

	@Override
	public RandomGenerator<U> generator(final int genSize) {
		return createListGenerator(genSize, false)
				.map(this::convertJavaListToVavrCollection);
	}

	@Override
	public RandomGenerator<U> generatorWithEmbeddedEdgeCases(final int genSize) {
		return createListGenerator(genSize, true)
				.map(this::convertJavaListToVavrCollection);
	}

	@Override
	public Optional<ExhaustiveGenerator<U>> exhaustive(final long maxNumberOfSamples) {
		return ExhaustiveGenerators.list(
				this.elementArbitrary,
				this.minSize,
				this.maxSize,
				this.uniquenessExtractors,
				maxNumberOfSamples
		).map(exhaustiveGenerator -> exhaustiveGenerator.map(this::convertJavaListToVavrCollection));
	}

	@Override
	public EdgeCases<U> edgeCases(final int maxEdgeCases) {
		return EdgeCasesSupport.map(
				edgeCases((elements, minSize1) ->
						new ShrinkableList<>(elements, minSize1, this.maxSize, this.uniquenessExtractors),
						maxEdgeCases),
				this::convertJavaListToVavrCollection
		);
	}

}
