package net.jqwik.vavr.providers;

import io.vavr.Lazy;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.VavrLazyArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

public class VavrLazyArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Lazy.class;
    }

    @Override
    protected VavrLazyArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrLazyArbitrary<>(innerArbitrary);
    }

}
