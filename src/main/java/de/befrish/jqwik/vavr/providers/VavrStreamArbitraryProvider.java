package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.VavrStreamArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractVavrContainerArbitraryProvider;
import io.vavr.collection.Stream;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrStreamArbitraryProvider extends AbstractVavrContainerArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Stream.class;
    }

    @Override
    protected VavrStreamArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrStreamArbitrary<>(innerArbitrary, innerArbitrary.isUnique());
    }

}
