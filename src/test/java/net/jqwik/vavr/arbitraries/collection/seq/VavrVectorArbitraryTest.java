package net.jqwik.vavr.arbitraries.collection.seq;

import io.vavr.collection.Vector;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrVectorArbitraryTest extends VavrTraversableArbitraryTestBase<Vector<Integer>, Vector<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Vector<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.vector(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll @UniqueElements final Vector<Integer> vector) {
        assertThat(vector.distinct().size(), is(vector.size()));
    }

}
