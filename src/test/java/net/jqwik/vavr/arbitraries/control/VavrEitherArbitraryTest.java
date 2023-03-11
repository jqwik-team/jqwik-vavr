package net.jqwik.vavr.arbitraries.control;

import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.ArbitraryTestBase;
import io.vavr.control.Either;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.From;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.CharRange;
import net.jqwik.api.constraints.IntRange;

import static net.jqwik.vavr.arbitraries.TestMatchers.hasOnlyAs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

class VavrEitherArbitraryTest extends ArbitraryTestBase<Either<String, Integer>> {

    @Provide
    Arbitrary<Either<String, Integer>> integersMin3OrAString() {
        return VavrArbitraries.either(
                Arbitraries.strings().withCharRange('a', 'a'),
                Arbitraries.integers().greaterOrEqual(3));
    }

    @Property
    void generateFrom(@ForAll @From("integersMin3OrAString") final Either<String, Integer> either) {
        if (either.isLeft()) {
            assertThat(either.getLeft(), hasOnlyAs());
        } else {
            assertThat(either.get(), is(greaterThanOrEqualTo(3)));
        }
    }

    @Property
    void generateWithValueConstraints(@ForAll final Either<@CharRange(from = 'a', to = 'a') String, @IntRange(min = 3, max = 42) Integer> either) {
        if (either.isLeft()) {
            assertThat(either.getLeft(), is(hasOnlyAs()));
        } else {
            assertThat(either.get(), is(greaterThanOrEqualTo(3)));
            assertThat(either.get(), is(lessThanOrEqualTo(42)));
        }
    }

}
