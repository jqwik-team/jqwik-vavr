package net.jqwik.vavr.arbitraries.collection.seq;

import io.vavr.collection.LinearSeq;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrLinearSeqArbitraryTest extends VavrTraversableArbitraryTestBase<LinearSeq<Integer>, LinearSeq<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends LinearSeq<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.linearSeq(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll @UniqueElements final LinearSeq<Integer> linearSeq) {
        assertThat(linearSeq.distinct().size(), is(linearSeq.size()));
    }

}
