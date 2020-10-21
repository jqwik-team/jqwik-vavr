package de.befrish.jqwik.vavr.collection.seq;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.Array;
import io.vavr.collection.Vector;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.Unique;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrVectorArbitraryTest extends VavrTraversableArbitraryTestBase<Vector<Integer>, Vector<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Vector<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.vector(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final Vector<@Unique Integer> vector) {
        assertThat(vector.distinct().size(), is(vector.size()));
    }

}
