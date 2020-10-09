package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.VavrArbitraries;
import io.vavr.Lazy;
import io.vavr.collection.List;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.From;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.NotEmpty;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.Unique;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;

/**
 * @author Benno Müller
 */
class VavrLazyArbitraryProviderTest {

    @Property
    void generate(@ForAll final Lazy<Integer> lazy) {
        // Test that the ArbitraryProvider works (fails if not found)
    }

    @Property
    void generateWithValueContrains(@ForAll final Lazy<@IntRange(min = 3, max = 42) Integer> lazy) {
        final Integer value = lazy.get();
        assertThat(value, is(greaterThanOrEqualTo(3)));
        assertThat(value, is(lessThanOrEqualTo(42)));
    }

    @Provide
    Arbitrary<Lazy<Integer>> integersMin3() {
        return VavrArbitraries.lazy(Arbitraries.integers().greaterOrEqual(3));
    }

    @Property
    void generateFrom(@ForAll @From("integersMin3") final Lazy<Integer> lazy) {
        assertThat(lazy.get(), is(greaterThanOrEqualTo(3)));
    }

}
