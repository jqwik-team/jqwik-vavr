package net.jqwik.vavr.configurators;

import java.util.function.Function;

import io.vavr.collection.Set;
import io.vavr.collection.Traversable;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.configurators.ArbitraryConfigurator;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.api.providers.TypeUsage;
import net.jqwik.vavr.arbitraries.base.ListBasedArbitrary;
import net.jqwik.vavr.arbitraries.base.SetBasedArbitrary;

// TODO: Should be redundant as soon as jqwik provides an interface for
//  arbitraries that can have unique values.
//  See https://github.com/jqwik-team/jqwik/issues/466
public class VavrUniqueElementsConfigurator implements ArbitraryConfigurator {

	@SuppressWarnings("unchecked")
	@Override
	public <T> Arbitrary<T> configure(Arbitrary<T> arbitrary, TypeUsage targetType) {
		return targetType.findAnnotation(UniqueElements.class).map(uniqueness -> {
			if (arbitrary instanceof ListBasedArbitrary) {
				return (Arbitrary<T>) configureListArbitrary((ListBasedArbitrary<?, ?>) arbitrary, uniqueness);
			}
			if (arbitrary instanceof SetBasedArbitrary) {
				return (Arbitrary<T>) configureSetArbitrary((SetBasedArbitrary<?, ?>) arbitrary, uniqueness);
			}
			return arbitrary;
		}).orElse(arbitrary);
	}

	@SuppressWarnings("unchecked")
	private <T, U extends Set<T>> Arbitrary<U> configureSetArbitrary(SetBasedArbitrary<T, U> arbitrary, UniqueElements uniqueness) {
		Function<T, Object> extractor = (Function<T, Object>) extractor(uniqueness);
		return arbitrary.uniqueElements(extractor);
	}

	@SuppressWarnings("unchecked")
	private <T, U extends Traversable<T>> Arbitrary<U> configureListArbitrary(ListBasedArbitrary<T, U> arbitrary, UniqueElements uniqueness) {
		Function<T, Object> extractor = (Function<T, Object>) extractor(uniqueness);
		return arbitrary.uniqueElements(extractor);
	}

	private static Function<?, Object> extractor(final UniqueElements uniqueness) {
		final Class<? extends Function<?, Object>> extractorClass = uniqueness.by();
			if (extractorClass.equals(UniqueElements.NOT_SET.class)) return Function.identity();
			throw new IllegalArgumentException("Vavr types do not support UniqueElements.by");
	}

}
