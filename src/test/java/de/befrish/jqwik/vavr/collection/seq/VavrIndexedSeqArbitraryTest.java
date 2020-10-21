package de.befrish.jqwik.vavr.collection.seq;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.IndexedSeq;
import io.vavr.collection.LinearSeq;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.Unique;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrIndexedSeqArbitraryTest extends VavrTraversableArbitraryTestBase<IndexedSeq<Integer>, IndexedSeq<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends IndexedSeq<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.indexedSeq(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final IndexedSeq<@Unique Integer> indexedSeq) {
        assertThat(indexedSeq.distinct().size(), is(indexedSeq.size()));
    }

}
