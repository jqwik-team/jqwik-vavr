package net.jqwik.vavr.arbitraries.base;

import io.vavr.collection.Set;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.ExhaustiveGenerator;
import net.jqwik.api.RandomGenerator;
import net.jqwik.api.Shrinkable;
import net.jqwik.engine.properties.FeatureExtractor;
import net.jqwik.engine.properties.arbitraries.EdgeCasesSupport;
import net.jqwik.engine.properties.arbitraries.exhaustive.ExhaustiveGenerators;
import net.jqwik.engine.properties.arbitraries.randomized.RandomGenerators;
import net.jqwik.engine.properties.shrinking.ShrinkableSet;

import java.util.HashSet;
import java.util.Optional;

public abstract class SetBasedArbitrary<T, U extends Set<T>> extends MultivalueArbitrary<T, U> {

	public SetBasedArbitrary(final Arbitrary<T> elementArbitrary) {
		super(elementArbitrary);
		this.uniquenessExtractors.add(FeatureExtractor.identity());
	}

	protected abstract U convertJavaSetToVavrCollection(java.util.Set<T> javaSet);

	@Override
	protected Iterable<T> toIterable(final U streamable) {
		return streamable;
	}

	@Override
	public RandomGenerator<U> generator(final int genSize) {
		return rawGenerator(genSize, false);
	}

	@Override
	public RandomGenerator<U> generatorWithEmbeddedEdgeCases(final int genSize) {
		return rawGenerator(genSize, true);
	}

	private RandomGenerator<U> rawGenerator(final int genSize, final boolean withEmbeddedEdgeCases) {
		final RandomGenerator<T> elementGenerator = elementGenerator(this.elementArbitrary, genSize, withEmbeddedEdgeCases);
		return RandomGenerators
				.set(
						elementGenerator,
						this.minSize,
						this.maxSize,
						genSize,
						this.sizeDistribution,
						this.uniquenessExtractors)
				.map(this::convertJavaSetToVavrCollection);
	}

	@Override
	public Optional<ExhaustiveGenerator<U>> exhaustive(final long maxNumberOfSamples) {
		return ExhaustiveGenerators.set(
				this.elementArbitrary,
				this.minSize,
				this.maxSize,
				this.uniquenessExtractors,
				maxNumberOfSamples
		).map(exhaustiveGenerator -> exhaustiveGenerator.map(this::convertJavaSetToVavrCollection));
	}

	@Override
	public EdgeCases<U> edgeCases(final int maxEdgeCases) {
		return EdgeCasesSupport.map(
				edgeCases((elementList, minSize1) -> {
					final java.util.Set<Shrinkable<T>> elementSet = new HashSet<>(elementList);
					return new ShrinkableSet<>(elementSet, minSize1, this.maxSize, this.uniquenessExtractors);
				}, maxEdgeCases),
				this::convertJavaSetToVavrCollection
		);
	}

}
