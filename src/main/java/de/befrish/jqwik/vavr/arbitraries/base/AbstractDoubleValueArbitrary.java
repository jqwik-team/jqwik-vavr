package de.befrish.jqwik.vavr.arbitraries.base;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.RandomGenerator;

public abstract class AbstractDoubleValueArbitrary<T1, T2, U> implements Arbitrary<U> {

    private final Arbitrary<T1> firstArbitrary;
    private final Arbitrary<T2> secondArbitrary;

    public AbstractDoubleValueArbitrary(final Arbitrary<T1> firstArbitrary, final Arbitrary<T2> secondArbitrary) {
        this.firstArbitrary = firstArbitrary;
        this.secondArbitrary = secondArbitrary;
    }

    protected abstract U mapFirstValue(T1 value);
    protected abstract U mapSecondValue(T2 value);

    @Override
    public RandomGenerator<U> generator(final int genSize) {
        return Arbitraries.of(true, false)
                .generator(genSize)
                .flatMap(
                        firstValue -> firstValue
                                ? this.firstArbitrary.map(this::mapFirstValue)
                                : this.secondArbitrary.map(this::mapSecondValue),
                        genSize
                );

    }

    @Override
    public EdgeCases<U> edgeCases() {
        return Arbitraries.of(true, false)
                .edgeCases()
                .flatMapArbitrary(firstValue -> firstValue
                        ? this.firstArbitrary.map(this::mapFirstValue)
                        : this.secondArbitrary.map(this::mapSecondValue));
    }

}
