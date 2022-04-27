package net.jqwik.vavr.providers.collection;

import io.vavr.collection.Stream;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.VavrStreamArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

public class VavrStreamArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Stream.class;
    }

    @Override
    protected VavrStreamArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrStreamArbitrary<>(innerArbitrary);
    }

}
