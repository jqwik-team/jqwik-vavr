package net.jqwik.vavr.arbitraries.control;

import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.ArbitraryTestBase;
import io.vavr.control.Try;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.From;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

class VavrTryArbitraryTest extends ArbitraryTestBase<Try<Integer>> {

    @Provide
    Arbitrary<Try<Integer>> integersMin3OrIOException() {
        return VavrArbitraries.try_(
                Arbitraries.integers().greaterOrEqual(3),
                Arbitraries.of(new IOException()));
    }

    @Property
    void generateFrom(@ForAll @From("integersMin3OrIOException") final Try<Integer> try_) {
        if (try_.isSuccess()) {
            assertThat(try_.get(), is(greaterThanOrEqualTo(3)));
        } else {
            assertThat(try_.getCause(), is(instanceOf(IOException.class)));
        }
    }

    @Property
    void generateWithValueConstraints(@ForAll final Try<@IntRange(min = 3, max = 42) Integer> try_) {
        if (try_.isSuccess()) {
            assertThat(try_.get(), is(greaterThanOrEqualTo(3)));
            assertThat(try_.get(), is(lessThanOrEqualTo(42)));
        } else {
            assertThat(try_.getCause(), is(instanceOf(Throwable.class)));
        }
    }

}
