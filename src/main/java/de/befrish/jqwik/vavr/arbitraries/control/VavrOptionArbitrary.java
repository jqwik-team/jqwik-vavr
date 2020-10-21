package de.befrish.jqwik.vavr.arbitraries.control;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractSingleValueArbitrary;
import io.vavr.control.Option;
import net.jqwik.api.Arbitrary;

public class VavrOptionArbitrary<T> extends AbstractSingleValueArbitrary<T, Option<T>> implements Arbitrary<Option<T>> {

    public VavrOptionArbitrary(final Arbitrary<T> innerArbitrary) {
        super(innerArbitrary);
    }

    @Override
    protected Option<T> mapValue(final T value) {
        return Option.of(value);
    }

}
