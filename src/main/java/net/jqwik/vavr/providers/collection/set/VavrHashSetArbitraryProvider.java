package net.jqwik.vavr.providers.collection.set;

import io.vavr.collection.HashSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.set.VavrHashSetArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

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
