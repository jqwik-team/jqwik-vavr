package net.jqwik.vavr.providers.collection.set;

import io.vavr.collection.LinkedHashSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.set.VavrLinkedHashSetArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

public class VavrLinkedHashSetArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return LinkedHashSet.class;
    }

    @Override
    protected VavrLinkedHashSetArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrLinkedHashSetArbitrary<>(innerArbitrary);
    }

}
