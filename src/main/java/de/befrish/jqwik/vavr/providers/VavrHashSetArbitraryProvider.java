package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.VavrHashSetArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractVavrContainerArbitraryProvider;
import io.vavr.collection.HashSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrHashSetArbitraryProvider extends AbstractVavrContainerArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return HashSet.class;
    }

    @Override
    protected VavrHashSetArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrHashSetArbitrary<>(innerArbitrary);
    }

    @Override
    public int priority() {
        return 2; //  provider for Set
    }

}
