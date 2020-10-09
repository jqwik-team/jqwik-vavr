package de.befrish.jqwik.vavr.arbitraries.impl;

import io.vavr.Lazy;
import net.jqwik.api.Arbitrary;

/**
 * @author Benno Müller
 */
public class DefaultVavrLazyArbitrary<T> extends SinglevalueArbitraryBase<T, Lazy<T>> implements VavrLazyArbitrary<T> {

    public DefaultVavrLazyArbitrary(final Arbitrary<T> innerArbitrary) {
        super(innerArbitrary);
    }

    @Override
    protected Lazy<T> mapValue(final T value) {
        return Lazy.of(() -> value);
    }

}
