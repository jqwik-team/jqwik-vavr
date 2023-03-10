package net.jqwik.vavr.arbitraries.control;

import net.jqwik.api.*;
import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.ArbitraryTestBase;
import io.vavr.control.Option;

import net.jqwik.api.constraints.IntRange;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

class VavrOptionArbitraryTest extends ArbitraryTestBase<Option<Integer>> {

    @Provide
    Arbitrary<Option<Integer>> integersMin3() {
        return VavrArbitraries.option(Arbitraries.integers().greaterOrEqual(3));
    }

    @Property
    void generateFrom(@ForAll @From("integersMin3") final Option<Integer> option) {
        assertThat(option.getOrElse(3), is(greaterThanOrEqualTo(3)));
    }

    @Property
    void generateWithValueConstraints(@ForAll final Option<@IntRange(min = 3, max = 42) Integer> option) {
        final Integer value = option.getOrElse(3);
        assertThat(value, is(greaterThanOrEqualTo(3)));
        assertThat(value, is(lessThanOrEqualTo(42)));
    }

}
