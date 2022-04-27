package net.jqwik.vavr.providers.control;

import io.vavr.control.Either;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.control.VavrEitherArbitrary;
import net.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;

public class VavrEitherArbitraryProvider extends AbstractDoubleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Either.class;
    }

    @Override
    protected Arbitrary<?> create(final Arbitrary<?> firstArbitrary, final Arbitrary<?> secondArbitrary) {
        return new VavrEitherArbitrary<>(firstArbitrary, secondArbitrary);
    }

}
