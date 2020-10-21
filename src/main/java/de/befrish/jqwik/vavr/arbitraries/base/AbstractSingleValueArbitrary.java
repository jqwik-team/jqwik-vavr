package de.befrish.jqwik.vavr.arbitraries.base;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.RandomGenerator;

public abstract class AbstractSingleValueArbitrary<T, U> implements Arbitrary<U> {

    private final Arbitrary<T> innerArbitrary;

    public AbstractSingleValueArbitrary(final Arbitrary<T> innerArbitrary) {
        this.innerArbitrary = innerArbitrary;
    }

    protected abstract U mapValue(T value);

    protected abstract EdgeCases<U> edgeCases(final Arbitrary<T> innerArbitrary);

    @Override
    public RandomGenerator<U> generator(final int genSize) {
        return this.innerArbitrary.generator(genSize).map(this::mapValue);
    }

    @Override
    public EdgeCases<U> edgeCases() {
        return edgeCases(this.innerArbitrary);
    }

}
