package net.jqwik.vavr.providers.collection;

import io.vavr.collection.Iterator;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.VavrIteratorArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

public class VavrIteratorArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Iterator.class;
    }

    @Override
    protected VavrIteratorArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrIteratorArbitrary<>(innerArbitrary);
    }

    @Override
    public int priority() {
        return 3; //  provider for Traversable
    }

}
