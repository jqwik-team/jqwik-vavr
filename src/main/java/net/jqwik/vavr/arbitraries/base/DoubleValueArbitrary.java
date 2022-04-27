package net.jqwik.vavr.arbitraries.base;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.RandomGenerator;
import net.jqwik.engine.properties.arbitraries.EdgeCasesSupport;

import java.util.Arrays;

public abstract class DoubleValueArbitrary<T1, T2, U> implements Arbitrary<U> {

    private final Arbitrary<T1> firstArbitrary;
    private final Arbitrary<T2> secondArbitrary;

    public DoubleValueArbitrary(final Arbitrary<T1> firstArbitrary, final Arbitrary<T2> secondArbitrary) {
        this.firstArbitrary = firstArbitrary;
        this.secondArbitrary = secondArbitrary;
    }

    protected abstract U mapFirstValue(T1 value);
    protected abstract U mapSecondValue(T2 value);

    @Override
    public RandomGenerator<U> generator(final int genSize) {
        return Arbitraries.of(true, false)
                .generator(genSize)
                .flatMap(this::getMappedArbitrary, genSize, false);
    }

    private Arbitrary<U> getMappedArbitrary(final Boolean firstValue) {
        return firstValue
                ? this.firstArbitrary.map(this::mapFirstValue)
                : this.secondArbitrary.map(this::mapSecondValue);
    }

    @Override
    public EdgeCases<U> edgeCases(final int maxEdgeCases) {
        final EdgeCases<U> firstEdgeCases = EdgeCasesSupport.map(this.firstArbitrary.edgeCases(), this::mapFirstValue);
        final EdgeCases<U> secondEdgeCases = EdgeCasesSupport.map(this.secondArbitrary.edgeCases(), this::mapSecondValue);
        return EdgeCasesSupport.concat(Arrays.asList(firstEdgeCases, secondEdgeCases), maxEdgeCases);
    }

}
