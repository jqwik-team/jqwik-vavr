package net.jqwik.vavr.arbitraries.collection.map;

import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrMapArbitraryTestBase;
import io.vavr.collection.LinkedHashMap;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.SizableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrLinkedHashMapArbitraryTest extends VavrMapArbitraryTestBase<LinkedHashMap<Integer, String>, LinkedHashMap<Boolean, Boolean>> {

    @Override
    protected SizableArbitrary<? extends LinkedHashMap<Integer, String>> createMapArbtitrary(
            final Arbitrary<Integer> keysArbitrary,
            final Arbitrary<String> valuesArbitrary) {
        return VavrArbitraries.linkedHashMap(keysArbitrary, valuesArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final LinkedHashMap<Integer, String> linkedHashMap) { // no @Unique because set should be always distinct
        assertThat(linkedHashMap.distinct().size(), is(linkedHashMap.size()));
    }

}
