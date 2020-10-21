package de.befrish.jqwik.vavr.collection.set;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.HashSet;
import io.vavr.collection.SortedSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrSortedSetArbitraryTest extends VavrTraversableArbitraryTestBase<SortedSet<Integer>, SortedSet<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends SortedSet<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.sortedSet(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final SortedSet<Integer> sortedSet) { // no @Unique because set should be always distinct
        assertThat(sortedSet.distinct().size(), is(sortedSet.size()));
    }

}
