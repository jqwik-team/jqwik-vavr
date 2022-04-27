package net.jqwik.vavr.arbitraries.collection.queue;

import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.PriorityQueue;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrPriorityQueueArbitraryTest extends VavrTraversableArbitraryTestBase<
        PriorityQueue<Integer>,
        PriorityQueue<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends PriorityQueue<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.priorityQueue(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll @UniqueElements final PriorityQueue<Integer> priorityQueue) {
        assertThat(priorityQueue.distinct().size(), is(priorityQueue.size()));
    }

}
