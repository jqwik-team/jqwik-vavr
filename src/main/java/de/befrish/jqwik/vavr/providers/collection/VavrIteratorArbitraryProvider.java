package de.befrish.jqwik.vavr.providers.collection;

import de.befrish.jqwik.vavr.arbitraries.collection.VavrIteratorArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;
import io.vavr.collection.Iterator;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrIteratorArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Iterator.class;
    }

    @Override
    protected VavrIteratorArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrIteratorArbitrary<>(innerArbitrary, innerArbitrary.isUnique());
    }

    @Override
    public int priority() {
        return 3; //  provider for Traversable
    }

}
