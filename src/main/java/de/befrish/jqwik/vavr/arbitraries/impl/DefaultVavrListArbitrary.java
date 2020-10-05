package de.befrish.jqwik.vavr.arbitraries.impl;

import de.befrish.jqwik.vavr.arbitraries.VavrListArbitrary;
import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.ExhaustiveGenerator;
import net.jqwik.api.RandomGenerator;
import net.jqwik.engine.properties.arbitraries.exhaustive.ExhaustiveGenerators;
import net.jqwik.engine.properties.shrinking.ShrinkableList;

import java.util.Optional;

public class DefaultVavrListArbitrary<T> extends MultivalueArbitraryBase<T, List<T>> implements VavrListArbitrary<T> {

	public DefaultVavrListArbitrary(final Arbitrary<T> elementArbitrary, final boolean elementsUnique) {
		super(elementArbitrary, elementsUnique);
	}

	@Override
	protected Iterable<T> toIterable(final List<T> streamable) {
		return streamable;
	}

	@Override
	public RandomGenerator<List<T>> generator(final int genSize) {
		return createListGenerator(genSize).map(List::ofAll);
	}

	@Override
	public Optional<ExhaustiveGenerator<List<T>>> exhaustive(final long maxNumberOfSamples) {
		return ExhaustiveGenerators.list(this.elementArbitrary, this.minSize, this.maxSize, maxNumberOfSamples)
				.map(exhaustiveGenerator -> exhaustiveGenerator.map(List::ofAll));
	}

	@Override
	public EdgeCases<List<T>> edgeCases() {
		return edgeCases((elements, minSize1) -> new ShrinkableList<>(elements, minSize1, this.maxSize))
				.map(List::ofAll);
	}

	@Override
	public VavrListArbitrary<T> ofMaxSize(final int maxSize) {
		return (VavrListArbitrary<T>) super.ofMaxSize(maxSize);
	}

	@Override
	public VavrListArbitrary<T> ofMinSize(final int minSize) {
		return (VavrListArbitrary<T>) super.ofMinSize(minSize);
	}

//	@Override
//	public <U> Arbitrary<List<U>> mapEach(final BiFunction<List<T>, T, U> mapper) {
//		return this.map(elements -> elements.stream()
//											.map(e -> mapper.apply(elements, e))
//											.collect(Collectors.toList()));
//	}
//
//	@Override
//	public <U> Arbitrary<List<U>> flatMapEach(final BiFunction<List<T>, T, Arbitrary<U>> flatMapper) {
//		return this.flatMap(elements -> {
//			final List<Arbitrary<U>> arbitraries =
//				elements.stream()
//						.map(e -> flatMapper.apply(elements, e))
//						.collect(Collectors.toList());
//			return Combinators.combine(arbitraries).as(ArrayList::new);
//		});
//	}
}
