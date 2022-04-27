package net.jqwik.vavr.arbitraries.control;

import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.ArbitraryTestBase;
import io.vavr.control.Validation;
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
import static org.hamcrest.Matchers.notNullValue;

class VavrValidationArbitraryTest extends ArbitraryTestBase<Validation<String, Integer>> {

    @Provide
    Arbitrary<Validation<String, Integer>> integersMin3OrAString() {
        return VavrArbitraries.validation(
                Arbitraries.strings().withCharRange('a', 'a'),
                Arbitraries.integers().greaterOrEqual(3));
    }

    @Property
    void generateFrom(@ForAll @From("integersMin3OrAString") final Validation<String, Integer> validation) {
        if (validation.isValid()) {
            assertThat(validation.get(), is(greaterThanOrEqualTo(3)));
        } else {
            assertThat(validation.getError(), hasOnlyAs());
        }
    }

    @Property
    void generateWithValueContraints(@ForAll final Validation<@CharRange(from = 'a', to = 'a') String, @IntRange(min = 3, max = 42) Integer> validation) {
        if (validation.isValid()) {
            assertThat(validation.get(), is(greaterThanOrEqualTo(3)));
            assertThat(validation.get(), is(lessThanOrEqualTo(42)));
        } else {
            assertThat(validation.getError(), is(hasOnlyAs()));
        }
    }

}
