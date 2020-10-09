package de.befrish.jqwik.vavr.arbitraries.impl;

import io.vavr.Lazy;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.RandomGenerator;

/**
 * @author Benno Müller
 */
public abstract class SinglevalueArbitraryBase<T, U> implements Arbitrary<U> {

    private final Arbitrary<T> innerArbitrary;

    public SinglevalueArbitraryBase(final Arbitrary<T> innerArbitrary) {
        this.innerArbitrary = innerArbitrary;
    }

    protected abstract U mapValue(T value);

    @Override
    public RandomGenerator<U> generator(int genSize) {
        return innerArbitrary.generator(genSize).map(this::mapValue);
    }

    @Override
    public EdgeCases<U> edgeCases() {
        return innerArbitrary.edgeCases().map(this::mapValue);
    }

}
