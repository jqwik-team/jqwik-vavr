package de.befrish.jqwik.vavr.providers.collection.queue;

import de.befrish.jqwik.vavr.arbitraries.collection.queue.VavrPriorityQueueArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;
import io.vavr.collection.PriorityQueue;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrPriorityQueueArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return PriorityQueue.class;
    }

    @Override
    protected VavrPriorityQueueArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrPriorityQueueArbitrary<>(innerArbitrary, innerArbitrary.isUnique());
    }

}
