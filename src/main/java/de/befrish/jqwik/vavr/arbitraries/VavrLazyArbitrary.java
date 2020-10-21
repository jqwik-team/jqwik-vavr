package de.befrish.jqwik.vavr.arbitraries;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractSingleValueArbitrary;
import io.vavr.Lazy;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;

public class VavrLazyArbitrary<T> extends AbstractSingleValueArbitrary<T, Lazy<T>> implements Arbitrary<Lazy<T>> {

    public VavrLazyArbitrary(final Arbitrary<T> innerArbitrary) {
        super(innerArbitrary);
    }

    @Override
    protected Lazy<T> mapValue(final T value) {
        return Lazy.of(() -> value);
    }

    @Override
    protected EdgeCases<Lazy<T>> edgeCases(final Arbitrary<T> innerArbitrary) {
        return innerArbitrary.edgeCases().map(this::mapValue);
    }

}
