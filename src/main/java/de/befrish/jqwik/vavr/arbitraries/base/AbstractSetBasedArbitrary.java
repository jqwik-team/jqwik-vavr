package de.befrish.jqwik.vavr.arbitraries.base;

import io.vavr.collection.Set;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.ExhaustiveGenerator;
import net.jqwik.api.RandomGenerator;
import net.jqwik.engine.properties.arbitraries.exhaustive.ExhaustiveGenerators;
import net.jqwik.engine.properties.arbitraries.randomized.RandomGenerators;
import net.jqwik.engine.properties.shrinking.ShrinkableSet;

import java.util.Optional;

public abstract class AbstractSetBasedArbitrary<T, U extends Set<T>> extends AbstractCollectionBasedArbitrary<T, U> {

	public AbstractSetBasedArbitrary(final Arbitrary<T> elementArbitrary) {
		super(elementArbitrary, true);
	}

	protected abstract U convertJavaSetToVavrCollection(java.util.Set<T> javaSet);

	@Override
	protected Iterable<T> toIterable(final U streamable) {
		return streamable;
	}

	@Override
	public RandomGenerator<U> generator(final int genSize) {
		final int cutoffSize = cutoffSize(genSize);
		final RandomGenerator<T> elementGenerator = elementGenerator(this.elementArbitrary, genSize);
		return RandomGenerators.set(elementGenerator, this.minSize, this.maxSize, cutoffSize)
				.withEdgeCases(genSize, edgeCases().map(Set::toJavaSet))
				.map(this::convertJavaSetToVavrCollection);
	}

	@Override
	public Optional<ExhaustiveGenerator<U>> exhaustive(final long maxNumberOfSamples) {
		return ExhaustiveGenerators.set(this.elementArbitrary, this.minSize, this.maxSize, maxNumberOfSamples)
				.map(exhaustiveGenerator -> exhaustiveGenerator.map(this::convertJavaSetToVavrCollection));
	}

	@Override
	public EdgeCases<U> edgeCases() {
		return edgeCases((elementList, minSize1) -> new ShrinkableSet<>(new java.util.HashSet<>(elementList), minSize1, this.maxSize))
				.map(this::convertJavaSetToVavrCollection);
	}

}
