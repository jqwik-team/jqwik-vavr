package de.befrish.jqwik.vavr.collection.seq;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.LinearSeq;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.Unique;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrLinearSeqArbitraryTest extends VavrTraversableArbitraryTestBase<LinearSeq<Integer>, LinearSeq<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends LinearSeq<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.linearSeq(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final LinearSeq<@Unique Integer> linearSeq) {
        assertThat(linearSeq.distinct().size(), is(linearSeq.size()));
    }

}
