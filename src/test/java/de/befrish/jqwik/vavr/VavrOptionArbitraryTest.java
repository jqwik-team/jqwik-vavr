package de.befrish.jqwik.vavr;

import io.vavr.control.Option;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.From;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

class VavrOptionArbitraryTest {

    @Provide
    Arbitrary<Option<Integer>> integersMin3() {
        return VavrArbitraries.option(Arbitraries.integers().greaterOrEqual(3));
    }

    @Property
    void generateFrom(@ForAll @From("integersMin3") final Option<Integer> option) {
        assertThat(option.getOrElse(3), is(greaterThanOrEqualTo(3)));
    }

    @Property
    void generate(@ForAll final Option<Integer> option) {
        // Test that the ArbitraryProvider works (fails if not found)
        assertThat(option, is(notNullValue()));
    }

    @Property
    void generateWithValueContraints(@ForAll final Option<@IntRange(min = 3, max = 42) Integer> option) {
        final Integer value = option.getOrElse(3);
        assertThat(value, is(greaterThanOrEqualTo(3)));
        assertThat(value, is(lessThanOrEqualTo(42)));
    }

}
