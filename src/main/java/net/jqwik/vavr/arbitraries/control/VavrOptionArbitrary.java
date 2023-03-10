package net.jqwik.vavr.arbitraries.control;

import net.jqwik.vavr.arbitraries.base.SingleValueArbitrary;
import io.vavr.control.Option;
import net.jqwik.api.Arbitrary;

public class VavrOptionArbitrary<T> extends SingleValueArbitrary<T, Option<T>> implements Arbitrary<Option<T>> {

    public VavrOptionArbitrary(final Arbitrary<T> innerArbitrary) {
        super(innerArbitrary);
    }

    @Override
    protected Option<T> mapValue(final T value) {
        return Option.of(value);
    }

}
