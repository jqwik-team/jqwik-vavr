package net.jqwik.vavr.arbitraries.collection.seq;

import io.vavr.collection.IndexedSeq;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrIndexedSeqArbitraryTest extends VavrTraversableArbitraryTestBase<IndexedSeq<Integer>, IndexedSeq<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends IndexedSeq<Integer>> createCollectionArbitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.indexedSeq(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll @UniqueElements final IndexedSeq<Integer> indexedSeq) {
        assertThat(indexedSeq.distinct().size(), is(indexedSeq.size()));
    }

}
