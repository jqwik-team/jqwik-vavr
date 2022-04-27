package net.jqwik.vavr.providers.collection.seq;

import io.vavr.collection.Array;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.seq.VavrArrayArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

public class VavrArrayArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Array.class;
    }

    @Override
    protected VavrArrayArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrArrayArbitrary<>(innerArbitrary);
    }

    @Override
    public int priority() {
        return 1; //  provider for IndexedSeq
    }

}
