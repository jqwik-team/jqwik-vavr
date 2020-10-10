package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.VavrBitSetArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrHashSetArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractVavrContainerArbitraryProvider;
import io.vavr.collection.BitSet;
import io.vavr.collection.HashSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrBitSetArbitraryProvider extends AbstractVavrContainerArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return BitSet.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected VavrBitSetArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrBitSetArbitrary<>(innerArbitrary);
    }

    @Override
    public int priority() {
        return -1; //  match last (very specialised)
    }

}