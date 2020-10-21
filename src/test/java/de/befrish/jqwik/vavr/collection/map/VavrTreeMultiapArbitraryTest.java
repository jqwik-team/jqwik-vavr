package de.befrish.jqwik.vavr.collection.map;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrMapArbitraryTestBase;
import io.vavr.collection.TreeMultimap;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.SizableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrTreeMultiapArbitraryTest extends VavrMapArbitraryTestBase<TreeMultimap<Integer, String>, TreeMultimap<Boolean, Boolean>> {

    @Override
    protected SizableArbitrary<? extends TreeMultimap<Integer, String>> createMapArbtitrary(
            final Arbitrary<Integer> keysArbitrary,
            final Arbitrary<String> valuesArbitrary) {
        return VavrArbitraries.treeMultimap(keysArbitrary, valuesArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final TreeMultimap<Integer, String> treeMultimap) { // no @Unique because set should be always distinct
        assertThat(treeMultimap.distinct().size(), is(treeMultimap.size()));
    }

}
