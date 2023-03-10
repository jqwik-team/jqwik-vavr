package net.jqwik.vavr.arbitraries.base;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.arbitraries.ArbitraryDecorator;

public abstract class DoubleValueArbitrary<T1, T2, U> extends ArbitraryDecorator<U> implements Arbitrary<U> {

    private final Arbitrary<T1> firstArbitrary;
    private final Arbitrary<T2> secondArbitrary;

    public DoubleValueArbitrary(final Arbitrary<T1> firstArbitrary, final Arbitrary<T2> secondArbitrary) {
        this.firstArbitrary = firstArbitrary;
        this.secondArbitrary = secondArbitrary;
    }

    protected abstract U mapFirstValue(T1 value);
    protected abstract U mapSecondValue(T2 value);

    @Override
    protected Arbitrary<U> arbitrary() {
        return Arbitraries.of(true, false).flatMap(this::getMappedArbitrary);
    }

    private Arbitrary<U> getMappedArbitrary(final Boolean firstValue) {
        return firstValue
                ? this.firstArbitrary.map(this::mapFirstValue)
                : this.secondArbitrary.map(this::mapSecondValue);
    }

}
