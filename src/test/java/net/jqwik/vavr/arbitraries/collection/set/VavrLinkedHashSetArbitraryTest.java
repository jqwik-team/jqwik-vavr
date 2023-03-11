package net.jqwik.vavr.arbitraries.collection.set;

import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.LinkedHashSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrLinkedHashSetArbitraryTest extends VavrTraversableArbitraryTestBase<LinkedHashSet<Integer>, LinkedHashSet<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends LinkedHashSet<Integer>> createCollectionArbitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.linkedHashSet(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final LinkedHashSet<Integer> linkedHashSet) { // no @Unique because set should be always distinct
        assertThat(linkedHashSet.distinct().size(), is(linkedHashSet.size()));
    }

}
