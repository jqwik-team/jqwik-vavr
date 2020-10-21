package de.befrish.jqwik.vavr.arbitraries.base;

import io.vavr.control.Option;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.RandomGenerator;
import net.jqwik.engine.properties.arbitraries.EdgeCasesSupport;

import java.util.Arrays;
import java.util.function.Function;

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
                .flatMap(this::getMappedArbitrary, genSize);
    }

    private Arbitrary<U> getMappedArbitrary(final Boolean firstValue) {
        return firstValue
                ? this.firstArbitrary.map(this::mapFirstValue)
                : this.secondArbitrary.map(this::mapSecondValue);
    }

    @Override
    public EdgeCases<U> edgeCases() {
        final EdgeCases<U> firstEdgeCases = this.firstArbitrary.edgeCases().map(this::mapFirstValue);
        final EdgeCases<U> secondEdgeCases = this.secondArbitrary.edgeCases().map(this::mapSecondValue);
        return EdgeCasesSupport.concat(Arrays.asList(firstEdgeCases, secondEdgeCases));
    }

}
