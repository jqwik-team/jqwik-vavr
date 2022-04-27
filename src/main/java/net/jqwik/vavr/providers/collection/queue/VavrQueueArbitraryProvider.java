package net.jqwik.vavr.providers.collection.queue;

import io.vavr.collection.Queue;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.queue.VavrQueueArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

public class VavrQueueArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Queue.class;
    }

    @Override
    protected VavrQueueArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrQueueArbitrary<>(innerArbitrary);
    }

}
