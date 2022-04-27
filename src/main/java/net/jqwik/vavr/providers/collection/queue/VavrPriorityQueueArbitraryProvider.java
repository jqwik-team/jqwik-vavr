package net.jqwik.vavr.providers.collection.queue;

import io.vavr.collection.PriorityQueue;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.queue.VavrPriorityQueueArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

public class VavrPriorityQueueArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return PriorityQueue.class;
    }

    @Override
    protected VavrPriorityQueueArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrPriorityQueueArbitrary<>(innerArbitrary);
    }

}
