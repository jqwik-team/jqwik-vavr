package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.arbitraries.VavrListArbitrary;
import io.vavr.collection.List;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.From;
import net.jqwik.api.FromData;
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
class VavrListArbitraryProviderTest {

    @Property
    void generateLists(@ForAll final List<Integer> list) {
        // Test that the ArbitraryProvider works (fails if not found)
    }

    @Property
    void generateDistinctLists(@ForAll final List<@Unique Integer> list) {
        assertThat(list.distinct().size(), is(list.size()));
    }


    @Property
    void generateSizableLists(@ForAll @Size(5) final List<Integer> list) {
        assertThat(list.size(), is(5));
    }

    @Property
    void generateNonEmptyLists(@ForAll @NotEmpty final List<Integer> list) {
        assertThat(list, is(not(emptyIterable())));
    }

    @Provide
    Arbitrary<List<Integer>> integersMin3() {
        return VavrArbitraries.list(Arbitraries.integers()).ofMinSize(3);
    }

    @Property
    void generateSizableListFrom(@ForAll @From("integersMin3") final List<Integer> list) {
        assertThat(list.size(), is(greaterThanOrEqualTo(3)));
    }

}
