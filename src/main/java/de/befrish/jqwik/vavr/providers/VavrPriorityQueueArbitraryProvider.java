package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.VavrPriorityQueueArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrQueueArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractVavrContainerArbitraryProvider;
import io.vavr.collection.PriorityQueue;
import io.vavr.collection.Queue;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrPriorityQueueArbitraryProvider extends AbstractVavrContainerArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return PriorityQueue.class;
    }

    @Override
    protected VavrPriorityQueueArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrPriorityQueueArbitrary<>(innerArbitrary, innerArbitrary.isUnique());
    }

}
