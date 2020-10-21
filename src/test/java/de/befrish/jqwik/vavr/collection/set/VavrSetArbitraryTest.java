package de.befrish.jqwik.vavr.collection.set;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.Set;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class VavrSetArbitraryTest extends VavrTraversableArbitraryTestBase<Set<Integer>, Set<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Set<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.set(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final Set<Integer> set) { // no @Unique because set should be always distinct
        assertThat(set.distinct().size(), is(set.size()));
    }

}
