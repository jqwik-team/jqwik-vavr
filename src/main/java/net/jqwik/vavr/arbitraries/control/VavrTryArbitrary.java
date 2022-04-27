package net.jqwik.vavr.arbitraries.control;

import net.jqwik.vavr.arbitraries.base.DoubleValueArbitrary;
import io.vavr.control.Try;
import net.jqwik.api.Arbitrary;

public class VavrTryArbitrary<T> extends DoubleValueArbitrary<T, Throwable, Try<T>>
        implements Arbitrary<Try<T>> {

    public VavrTryArbitrary(final Arbitrary<T> innerArbitrary, final Arbitrary<Throwable> exceptionArbitrary) {
        super(innerArbitrary, exceptionArbitrary);
    }

    @Override
    protected Try<T> mapFirstValue(final T value) {
        return Try.success(value);
    }

    @Override
    protected Try<T> mapSecondValue(final Throwable value) {
        return Try.failure(value);
    }

}
