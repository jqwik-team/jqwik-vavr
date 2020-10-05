package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.arbitraries.VavrListArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrSetArbitrary;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.From;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.NotEmpty;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.Unique;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * @author Benno Müller
 */
class VavrSetArbitraryProviderTest {

    @Property
    void generateLists(@ForAll final Set<Integer> set) {
        // Test that the ArbitraryProvider works (fails if not found)
    }

    @Property
    void generateDistinctLists(@ForAll final Set<Integer> set) {
        assertThat(set.distinct().size(), is(set.size()));
    }


    @Property
    void generateSizableLists(@ForAll @Size(5) final Set<Integer> set) {
        assertThat(set.size(), is(5));
    }

    @Property
    void generateNonEmptyLists(@ForAll @NotEmpty final Set<Integer> list) {
        assertThat(list, is(not(emptyIterable())));
    }

    @Provide
    Arbitrary<Set<Integer>> integersMin3() {
        return VavrArbitraries.set(Arbitraries.integers()).ofMinSize(3);
    }

    @Property
    void generateSizableListFromData(@ForAll @From("integersMin3") final Set<Integer> set) {
        assertThat(set.size(), is(greaterThanOrEqualTo(3)));
    }

}
