package de.befrish.jqwik.vavr.providers.collection.set;

import de.befrish.jqwik.vavr.arbitraries.collection.set.VavrBitSetArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;
import io.vavr.collection.BitSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrBitSetArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

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
