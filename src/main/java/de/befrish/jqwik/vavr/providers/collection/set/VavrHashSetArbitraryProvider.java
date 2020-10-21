package de.befrish.jqwik.vavr.providers.collection.set;

import de.befrish.jqwik.vavr.arbitraries.collection.set.VavrHashSetArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;
import io.vavr.collection.HashSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrHashSetArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

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
