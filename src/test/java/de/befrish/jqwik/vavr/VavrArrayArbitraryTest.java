package de.befrish.jqwik.vavr;

import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.Array;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.Unique;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrArrayArbitraryTest extends VavrTraversableArbitraryTestBase<Array<Integer>, Array<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Array<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.array(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final Array<@Unique Integer> array) {
        assertThat(array.distinct().size(), is(array.size()));
    }

}
