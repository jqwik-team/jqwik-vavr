package net.jqwik.vavr.arbitraries.concurrent;

import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.ArbitraryTestBase;
import io.vavr.concurrent.Future;
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
import static org.hamcrest.Matchers.notNullValue;

class VavrFutureArbitraryTest extends ArbitraryTestBase<Future<Integer>> {

    @Provide
    Arbitrary<Future<Integer>> integersMin3OrIOException() {
        return VavrArbitraries.future(
                Arbitraries.integers().greaterOrEqual(3),
                Arbitraries.of(new IOException()));
    }

    @Property
    void generateFrom(@ForAll @From("integersMin3OrIOException") final Future<Integer> future) {
        if (future.isSuccess()) {
            assertThat(future.get(), is(greaterThanOrEqualTo(3)));
        } else {
            assertThat(future.getCause().getOrElseThrow(() -> new AssertionError("no cause found")),
                    is(instanceOf(IOException.class)));
        }
    }

    @Property
    void generateWithValueContraints(@ForAll final Future<@IntRange(min = 3, max = 42) Integer> future) {
        if (future.isSuccess()) {
            assertThat(future.get(), is(greaterThanOrEqualTo(3)));
            assertThat(future.get(), is(lessThanOrEqualTo(42)));
        } else {
            assertThat(future.getCause().getOrElseThrow(() -> new AssertionError("no cause found")),
                    is(instanceOf(Throwable.class)));
        }
    }

}
