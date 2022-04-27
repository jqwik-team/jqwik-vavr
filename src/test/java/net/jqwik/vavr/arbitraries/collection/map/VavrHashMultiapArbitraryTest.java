package net.jqwik.vavr.arbitraries.collection.map;

import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrMapArbitraryTestBase;
import io.vavr.collection.HashMultimap;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.SizableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrHashMultiapArbitraryTest extends VavrMapArbitraryTestBase<HashMultimap<Integer, String>, HashMultimap<Boolean, Boolean>> {

    @Override
    protected SizableArbitrary<? extends HashMultimap<Integer, String>> createMapArbtitrary(
            final Arbitrary<Integer> keysArbitrary,
            final Arbitrary<String> valuesArbitrary) {
        return VavrArbitraries.hashMultimap(keysArbitrary, valuesArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final HashMultimap<Integer, String> hashMultimap) { // no @Unique because set should be always distinct
        assertThat(hashMultimap.distinct().size(), is(hashMultimap.size()));
    }

}
