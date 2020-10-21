package de.befrish.jqwik.vavr.arbitraries.control;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractDoubleValueArbitrary;
import io.vavr.control.Validation;
import net.jqwik.api.Arbitrary;

public class VavrValidationArbitrary<E, T> extends AbstractDoubleValueArbitrary<E, T, Validation<E, T>>
        implements Arbitrary<Validation<E, T>> {

    public VavrValidationArbitrary(final Arbitrary<E> failureArbitrary, final Arbitrary<T> innerArbitrary) {
        super(failureArbitrary, innerArbitrary);
    }

    @Override
    protected Validation<E, T> mapFirstValue(final E value) {
        return Validation.invalid(value);
    }

    @Override
    protected Validation<E, T> mapSecondValue(final T value) {
        return Validation.valid(value);
    }

}
