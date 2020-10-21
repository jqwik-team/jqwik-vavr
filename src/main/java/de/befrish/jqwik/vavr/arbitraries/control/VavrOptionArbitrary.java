package de.befrish.jqwik.vavr.arbitraries.control;

import de.befrish.jqwik.vavr.SingletonShrinkable;
import de.befrish.jqwik.vavr.arbitraries.base.AbstractSingleValueArbitrary;
import io.vavr.control.Option;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.engine.properties.arbitraries.EdgeCasesSupport;
import net.jqwik.engine.properties.shrinking.FixedValueFlatMappedShrinkable;
import net.jqwik.engine.properties.shrinking.SampleShrinkable;

import java.util.Arrays;

public class VavrOptionArbitrary<T> extends AbstractSingleValueArbitrary<T, Option<T>> implements Arbitrary<Option<T>> {

    public VavrOptionArbitrary(final Arbitrary<T> innerArbitrary) {
        super(innerArbitrary);
    }

    @Override
    protected Option<T> mapValue(final T value) {
        return Option.of(value);
    }

    @Override
    protected EdgeCases<Option<T>> edgeCases(final Arbitrary<T> innerArbitrary) {
        final EdgeCases<Option<T>> noneOptionEdgeCase = EdgeCases.fromSupplier(() -> SingletonShrinkable.of(Option.none()));
        final EdgeCases<Option<T>> someOptionEdgeCase = innerArbitrary.edgeCases().map(this::mapValue);
        return EdgeCasesSupport.concat(Arrays.asList(noneOptionEdgeCase, someOptionEdgeCase));
    }

}
