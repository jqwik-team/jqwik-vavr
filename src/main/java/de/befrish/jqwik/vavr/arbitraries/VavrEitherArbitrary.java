package de.befrish.jqwik.vavr.arbitraries;

import de.befrish.jqwik.vavr.arbitraries.base.DoublevalueArbitraryBase;
import de.befrish.jqwik.vavr.arbitraries.base.SinglevalueArbitraryBase;
import io.vavr.control.Either;
import io.vavr.control.Option;
import net.jqwik.api.Arbitrary;

public class VavrEitherArbitrary<L, R> extends DoublevalueArbitraryBase<L, R, Either<L, R>>
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
