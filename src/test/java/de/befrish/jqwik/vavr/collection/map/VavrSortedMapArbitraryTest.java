package de.befrish.jqwik.vavr.collection.map;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrMapArbitraryTestBase;
import io.vavr.collection.Map;
import io.vavr.collection.SortedMap;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.SizableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrSortedMapArbitraryTest extends VavrMapArbitraryTestBase<SortedMap<Integer, String>, SortedMap<Boolean, Boolean>> {

    @Override
    protected SizableArbitrary<? extends SortedMap<Integer, String>> createMapArbtitrary(
            final Arbitrary<Integer> keysArbitrary,
            final Arbitrary<String> valuesArbitrary) {
        return VavrArbitraries.sortedMap(keysArbitrary, valuesArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final SortedMap<Integer, String> sortedMap) { // no @Unique because set should be always distinct
        assertThat(sortedMap.distinct().size(), is(sortedMap.size()));
    }

}
