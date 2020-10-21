package de.befrish.jqwik.vavr;

import de.befrish.jqwik.vavr.base.VavrArbitraryTestBase;
import io.vavr.Lazy;
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

class VavrLazyArbitraryTest extends VavrArbitraryTestBase<Lazy<Integer>> {

    @Provide
    Arbitrary<Lazy<Integer>> integersMin3() {
        return VavrArbitraries.lazy(Arbitraries.integers().greaterOrEqual(3));
    }

    @Property
    void generateFrom(@ForAll @From("integersMin3") final Lazy<Integer> lazy) {
        assertThat(lazy.get(), is(greaterThanOrEqualTo(3)));
    }

    @Property
    void generateWithValueContraints(@ForAll final Lazy<@IntRange(min = 3, max = 42) Integer> lazy) {
        final Integer value = lazy.get();
        assertThat(value, is(greaterThanOrEqualTo(3)));
        assertThat(value, is(lessThanOrEqualTo(42)));
    }

}
