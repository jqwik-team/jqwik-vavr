package de.befrish.jqwik.vavr.arbitraries.impl;

import de.befrish.jqwik.vavr.arbitraries.VavrSetArbitrary;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.ExhaustiveGenerator;
import net.jqwik.api.RandomGenerator;
import net.jqwik.engine.properties.arbitraries.exhaustive.ExhaustiveGenerators;
import net.jqwik.engine.properties.arbitraries.randomized.RandomGenerators;
import net.jqwik.engine.properties.shrinking.ShrinkableSet;

import java.util.Optional;

public class DefaultVavrSetArbitrary<T> extends MultivalueArbitraryBase<T, Set<T>> implements VavrSetArbitrary<T> {

	public DefaultVavrSetArbitrary(Arbitrary<T> elementArbitrary) {
		super(elementArbitrary, true);
	}

	@Override
	protected Iterable<T> toIterable(Set<T> streamable) {
		return streamable;
	}

	@Override
	public RandomGenerator<Set<T>> generator(int genSize) {
		int cutoffSize = cutoffSize(genSize);
		RandomGenerator<T> elementGenerator = elementGenerator(elementArbitrary, genSize);
		return RandomGenerators.set(elementGenerator, minSize, maxSize, cutoffSize)
				.withEdgeCases(genSize, edgeCases().map(Set::toJavaSet))
				.map(HashSet::ofAll);
	}

	@Override
	public Optional<ExhaustiveGenerator<Set<T>>> exhaustive(long maxNumberOfSamples) {
		return ExhaustiveGenerators.set(elementArbitrary, minSize, maxSize, maxNumberOfSamples)
				.map(exhaustiveGenerator -> exhaustiveGenerator.map(HashSet::ofAll));
	}

	@Override
	public EdgeCases<Set<T>> edgeCases() {
		return edgeCases((elementList, minSize1) -> new ShrinkableSet<>(new java.util.HashSet<>(elementList), minSize1, maxSize))
				.map(HashSet::ofAll);
	}

	@Override
	public VavrSetArbitrary<T> ofMaxSize(int maxSize) {
		return (VavrSetArbitrary<T>) super.ofMaxSize(maxSize);
	}

	@Override
	public VavrSetArbitrary<T> ofMinSize(int minSize) {
		return (VavrSetArbitrary<T>) super.ofMinSize(minSize);
	}

//	@Override
//	public <U> Arbitrary<java.util.Set<U>> mapEach(BiFunction<java.util.Set<T>, T, U> mapper) {
//		return this.map(elements -> elements.stream()
//				.map(e -> mapper.apply(elements, e))
//				.collect(Collectors.toSet()));
//	}
//
//	@Override
//	public <U> Arbitrary<java.util.Set<U>> flatMapEach(BiFunction<java.util.Set<T>, T, Arbitrary<U>> flatMapper) {
//		return this.flatMap(elements -> {
//			List<Arbitrary<U>> arbitraries =
//					elements.stream()
//							.map(e -> flatMapper.apply(elements, e))
//							.collect(Collectors.toList());
//			return Combinators.combine(arbitraries).as(HashSet::new);
//		});
//	}
}
