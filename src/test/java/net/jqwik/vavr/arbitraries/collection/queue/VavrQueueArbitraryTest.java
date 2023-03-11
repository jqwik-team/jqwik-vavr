package net.jqwik.vavr.arbitraries.collection.queue;

import io.vavr.collection.Queue;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrQueueArbitraryTest extends VavrTraversableArbitraryTestBase<Queue<Integer>, Queue<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Queue<Integer>> createCollectionArbitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.queue(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll @UniqueElements final Queue<Integer> queue) {
        assertThat(queue.distinct().size(), is(queue.size()));
    }

}
