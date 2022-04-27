package net.jqwik.vavr.arbitraries.collection.map;

import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrMapArbitraryTestBase;
import io.vavr.collection.Map;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.SizableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrMapArbitraryTest extends VavrMapArbitraryTestBase<Map<Integer, String>, Map<Boolean, Boolean>> {

    @Override
    protected SizableArbitrary<? extends Map<Integer, String>> createMapArbtitrary(
            final Arbitrary<Integer> keysArbitrary,
            final Arbitrary<String> valuesArbitrary) {
        return VavrArbitraries.map(keysArbitrary, valuesArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final Map<Integer, String> map) { // no @Unique because set should be always distinct
        assertThat(map.distinct().size(), is(map.size()));
    }

}
