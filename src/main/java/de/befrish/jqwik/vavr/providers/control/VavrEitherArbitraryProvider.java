package de.befrish.jqwik.vavr.providers.control;

import de.befrish.jqwik.vavr.arbitraries.control.VavrEitherArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;
import io.vavr.control.Either;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
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
