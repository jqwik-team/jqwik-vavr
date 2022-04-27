package net.jqwik.vavr.providers.collection.set;

import io.vavr.collection.TreeSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.set.VavrTreeSetArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

public class VavrTreeSetArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return TreeSet.class;
    }

    @Override
    protected VavrTreeSetArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrTreeSetArbitrary<>(innerArbitrary);
    }

    @Override
    public int priority() {
        return 1; //  provider for SortedSet
    }

}
