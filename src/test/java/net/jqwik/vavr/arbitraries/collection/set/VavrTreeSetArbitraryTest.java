package net.jqwik.vavr.arbitraries.collection.set;

import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.TreeSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrTreeSetArbitraryTest extends VavrTraversableArbitraryTestBase<TreeSet<Integer>, TreeSet<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends TreeSet<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.treeSet(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final TreeSet<Integer> treeSet) { // no @Unique because set should be always distinct
        assertThat(treeSet.distinct().size(), is(treeSet.size()));
    }

}
