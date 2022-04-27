package net.jqwik.vavr.arbitraries.control;

import net.jqwik.vavr.SingletonShrinkable;
import net.jqwik.vavr.arbitraries.base.SingleValueArbitrary;
import io.vavr.control.Option;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.engine.properties.arbitraries.EdgeCasesSupport;

import java.util.Arrays;

public class VavrOptionArbitrary<T> extends SingleValueArbitrary<T, Option<T>> implements Arbitrary<Option<T>> {

    public VavrOptionArbitrary(final Arbitrary<T> innerArbitrary) {
        super(innerArbitrary);
    }

    @Override
    protected Option<T> mapValue(final T value) {
        return Option.of(value);
    }

    @Override
    protected EdgeCases<Option<T>> edgeCases(final Arbitrary<T> innerArbitrary, final int maxEdgeCases) {
        final EdgeCases<Option<T>> noneOptionEdgeCase = EdgeCases.fromSupplier(() -> SingletonShrinkable.of(Option.none()));
        final EdgeCases<Option<T>> someOptionEdgeCase = EdgeCasesSupport.map(innerArbitrary.edgeCases(), this::mapValue);
        return EdgeCasesSupport.concat(Arrays.asList(noneOptionEdgeCase, someOptionEdgeCase), maxEdgeCases);
    }

}
