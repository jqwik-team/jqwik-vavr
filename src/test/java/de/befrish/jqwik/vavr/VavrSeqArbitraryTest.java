package de.befrish.jqwik.vavr;

import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.Seq;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.Unique;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrSeqArbitraryTest extends VavrTraversableArbitraryTestBase<Seq<Integer>, Seq<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Seq<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.seq(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final Seq<@Unique Integer> seq) {
        assertThat(seq.distinct().size(), is(seq.size()));
    }

}
