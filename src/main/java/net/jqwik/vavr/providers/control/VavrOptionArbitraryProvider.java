package net.jqwik.vavr.providers.control;

import io.vavr.control.Option;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.control.VavrOptionArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

public class VavrOptionArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Option.class;
    }

    @Override
    protected VavrOptionArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrOptionArbitrary<>(innerArbitrary.injectNull(0.05));
    }

}
