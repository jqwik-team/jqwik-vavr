package net.jqwik.vavr.arbitraries.collection.map;

import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrMapArbitraryTestBase;
import io.vavr.collection.LinkedHashMultimap;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.SizableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrLinkedHashMultiapArbitraryTest extends VavrMapArbitraryTestBase<LinkedHashMultimap<Integer, String>, LinkedHashMultimap<Boolean, Boolean>> {

    @Override
    protected SizableArbitrary<? extends LinkedHashMultimap<Integer, String>> createMapArbtitrary(
            final Arbitrary<Integer> keysArbitrary,
            final Arbitrary<String> valuesArbitrary) {
        return VavrArbitraries.linkedHashMultimap(keysArbitrary, valuesArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final LinkedHashMultimap<Integer, String> linkedHashMultimap) { // no @Unique because set should be always distinct
        assertThat(linkedHashMultimap.distinct().size(), is(linkedHashMultimap.size()));
    }

}
