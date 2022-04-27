package net.jqwik.vavr.providers.collection;

import io.vavr.collection.Tree;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.VavrTreeArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

public class VavrTreeArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Tree.class;
    }

    @Override
    protected VavrTreeArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrTreeArbitrary<>(innerArbitrary);
    }

}
