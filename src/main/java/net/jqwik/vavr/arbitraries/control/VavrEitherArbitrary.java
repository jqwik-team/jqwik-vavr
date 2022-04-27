package net.jqwik.vavr.arbitraries.control;

import net.jqwik.vavr.arbitraries.base.DoubleValueArbitrary;
import io.vavr.control.Either;
import net.jqwik.api.Arbitrary;

public class VavrEitherArbitrary<L, R> extends DoubleValueArbitrary<L, R, Either<L, R>>
        implements Arbitrary<Either<L, R>> {

    public VavrEitherArbitrary(final Arbitrary<L> leftArbitrary, final Arbitrary<R> rightArbitrary) {
        super(leftArbitrary, rightArbitrary);
    }

    @Override
    protected Either<L, R> mapFirstValue(final L value) {
        return Either.left(value);
    }

    @Override
    protected Either<L, R> mapSecondValue(final R value) {
        return Either.right(value);
    }

}
