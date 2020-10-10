package de.befrish.jqwik.vavr.arbitraries.base;

import io.vavr.collection.Set;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.ExhaustiveGenerator;
import net.jqwik.api.RandomGenerator;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.engine.properties.arbitraries.exhaustive.ExhaustiveGenerators;
import net.jqwik.engine.properties.arbitraries.randomized.RandomGenerators;
import net.jqwik.engine.properties.shrinking.ShrinkableSet;

import java.util.Optional;

public abstract class AbstractSetBasedArbitrary<T, U extends Set<T>> extends MultivalueArbitraryBase<T, U>
		implements StreamableArbitrary<T, U> {

	public AbstractSetBasedArbitrary(Arbitrary<T> elementArbitrary) {
		super(elementArbitrary, true);
	}

	protected abstract U convertJavaSetToVavrCollection(java.util.Set<T> javaSet);

	@Override
	protected Iterable<T> toIterable(U streamable) {
		return streamable;
	}

	@Override
	public RandomGenerator<U> generator(int genSize) {
		int cutoffSize = cutoffSize(genSize);
		RandomGenerator<T> elementGenerator = elementGenerator(elementArbitrary, genSize);
		return RandomGenerators.set(elementGenerator, minSize, maxSize, cutoffSize)
				.withEdgeCases(genSize, edgeCases().map(Set::toJavaSet))
				.map(this::convertJavaSetToVavrCollection);
	}

	@Override
	public Optional<ExhaustiveGenerator<U>> exhaustive(long maxNumberOfSamples) {
		return ExhaustiveGenerators.set(elementArbitrary, minSize, maxSize, maxNumberOfSamples)
				.map(exhaustiveGenerator -> exhaustiveGenerator.map(this::convertJavaSetToVavrCollection));
	}

	@Override
	public EdgeCases<U> edgeCases() {
		return edgeCases((elementList, minSize1) -> new ShrinkableSet<>(new java.util.HashSet<>(elementList), minSize1, maxSize))
				.map(this::convertJavaSetToVavrCollection);
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
