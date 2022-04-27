package net.jqwik.vavr.providers.collection.set;

import io.vavr.collection.BitSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.set.VavrBitSetArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

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
