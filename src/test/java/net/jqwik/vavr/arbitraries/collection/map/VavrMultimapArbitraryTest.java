package net.jqwik.vavr.arbitraries.collection.map;

import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrMapArbitraryTestBase;
import io.vavr.collection.Multimap;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.SizableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrMultimapArbitraryTest extends VavrMapArbitraryTestBase<Multimap<Integer, String>, Multimap<Boolean, Boolean>> {

    @Override
    protected SizableArbitrary<? extends Multimap<Integer, String>> createMapArbtitrary(
            final Arbitrary<Integer> keysArbitrary,
            final Arbitrary<String> valuesArbitrary) {
        return VavrArbitraries.multimap(keysArbitrary, valuesArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final Multimap<Integer, String> multimap) { // no @Unique because set should be always distinct
        assertThat(multimap.distinct().size(), is(multimap.size()));
    }

}
