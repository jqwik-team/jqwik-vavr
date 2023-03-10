package net.jqwik.vavr.arbitraries;

import net.jqwik.vavr.arbitraries.base.SingleValueArbitrary;
import io.vavr.Lazy;
import net.jqwik.api.Arbitrary;

public class VavrLazyArbitrary<T> extends SingleValueArbitrary<T, Lazy<T>> implements Arbitrary<Lazy<T>> {

    public VavrLazyArbitrary(final Arbitrary<T> innerArbitrary) {
        super(innerArbitrary);
    }

    @Override
    protected Lazy<T> mapValue(final T value) {
        return Lazy.of(() -> value);
    }

}
