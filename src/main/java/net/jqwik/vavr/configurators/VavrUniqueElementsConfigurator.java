package net.jqwik.vavr.configurators;

import java.util.function.*;

import io.vavr.collection.*;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.configurators.ArbitraryConfigurator;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.api.providers.TypeUsage;
import net.jqwik.vavr.arbitraries.base.ListBasedVavrArbitrary;

public class VavrUniqueElementsConfigurator implements ArbitraryConfigurator {

	@SuppressWarnings("unchecked")
	@Override
	public <T> Arbitrary<T> configure(Arbitrary<T> arbitrary, TypeUsage targetType) {
		return targetType.findAnnotation(UniqueElements.class).map(uniqueness -> {
			if (arbitrary instanceof ListBasedVavrArbitrary) {
				return (Arbitrary<T>) configureListArbitrary((ListBasedVavrArbitrary<?, ?>) arbitrary, uniqueness);
			}
			return arbitrary;
		}).orElse(arbitrary);
	}

	@SuppressWarnings("unchecked")
	private <T, U extends Traversable<T>> Arbitrary<U> configureListArbitrary(ListBasedVavrArbitrary<T, U> arbitrary, UniqueElements uniqueness) {
		Function<T, Object> extractor = (Function<T, Object>) extractor(uniqueness);
		return arbitrary.uniqueElements(extractor);
	}

	private static Function<?, Object> extractor(final UniqueElements uniqueness) {
		final Class<? extends Function<?, Object>> extractorClass = uniqueness.by();
			if (extractorClass.equals(UniqueElements.NOT_SET.class)) return Function.identity();
			throw new IllegalArgumentException("Vavr collections do not support UniqueElements.by functions");
	}

}
