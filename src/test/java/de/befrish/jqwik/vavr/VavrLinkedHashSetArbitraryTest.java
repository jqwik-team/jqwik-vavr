package de.befrish.jqwik.vavr;

import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.LinkedHashSet;
import io.vavr.collection.Set;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrLinkedHashSetArbitraryTest extends VavrTraversableArbitraryTestBase<LinkedHashSet<Integer>, LinkedHashSet<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends LinkedHashSet<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.linkedHashSet(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final LinkedHashSet<Integer> linkedHashSet) { // no @Unique because set should be always distinct
        assertThat(linkedHashSet.distinct().size(), is(linkedHashSet.size()));
    }

}
