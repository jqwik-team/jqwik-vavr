package de.befrish.jqwik.vavr.collection.map;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrMapArbitraryTestBase;
import io.vavr.collection.Multimap;
import io.vavr.collection.SortedMultimap;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.SizableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrSortedMultimapArbitraryTest extends VavrMapArbitraryTestBase<SortedMultimap<Integer, String>, SortedMultimap<Boolean, Boolean>> {

    @Override
    protected SizableArbitrary<? extends SortedMultimap<Integer, String>> createMapArbtitrary(
            final Arbitrary<Integer> keysArbitrary,
            final Arbitrary<String> valuesArbitrary) {
        return VavrArbitraries.sortedMultimap(keysArbitrary, valuesArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final SortedMultimap<Integer, String> sortedMultimap) { // no @Unique because set should be always distinct
        assertThat(sortedMultimap.distinct().size(), is(sortedMultimap.size()));
    }

}
