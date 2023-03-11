package net.jqwik.vavr.arbitraries.collection.seq;

import io.vavr.collection.Array;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrArrayArbitraryTest extends VavrTraversableArbitraryTestBase<Array<Integer>, Array<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Array<Integer>> createCollectionArbitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.array(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll @UniqueElements final Array<Integer> array) {
        assertThat(array.distinct().size(), is(array.size()));
    }

}
