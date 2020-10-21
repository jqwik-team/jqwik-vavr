package de.befrish.jqwik.vavr.collection.map;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrMapArbitraryTestBase;
import io.vavr.collection.HashMap;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.SizableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrHashMapArbitraryTest extends VavrMapArbitraryTestBase<HashMap<Integer, String>, HashMap<Boolean, Boolean>> {

    @Override
    protected SizableArbitrary<? extends HashMap<Integer, String>> createMapArbtitrary(
            final Arbitrary<Integer> keysArbitrary,
            final Arbitrary<String> valuesArbitrary) {
        return VavrArbitraries.hashMap(keysArbitrary, valuesArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final HashMap<Integer, String> hashMap) { // no @Unique because set should be always distinct
        assertThat(hashMap.distinct().size(), is(hashMap.size()));
    }

}
