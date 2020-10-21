package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.VavrArrayArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;
import io.vavr.collection.Array;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrArrayArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Array.class;
    }

    @Override
    protected VavrArrayArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrArrayArbitrary<>(innerArbitrary, innerArbitrary.isUnique());
    }

    @Override
    public int priority() {
        return 1; //  provider for IndexedSeq
    }

}
