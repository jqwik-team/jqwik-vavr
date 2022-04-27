package net.jqwik.vavr.arbitraries.collection.map;

import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrMapArbitraryTestBase;
import io.vavr.collection.TreeMap;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.SizableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrTreeMapArbitraryTest extends VavrMapArbitraryTestBase<TreeMap<Integer, String>, TreeMap<Boolean, Boolean>> {

    @Override
    protected SizableArbitrary<? extends TreeMap<Integer, String>> createMapArbtitrary(
            final Arbitrary<Integer> keysArbitrary,
            final Arbitrary<String> valuesArbitrary) {
        return VavrArbitraries.treeMap(keysArbitrary, valuesArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final TreeMap<Integer, String> treeMap) { // no @Unique because set should be always distinct
        assertThat(treeMap.distinct().size(), is(treeMap.size()));
    }

}
