package de.befrish.jqwik.vavr.providers.control;

import de.befrish.jqwik.vavr.arbitraries.control.VavrValidationArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;
import io.vavr.control.Validation;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrValidationArbitraryProvider extends AbstractDoubleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Validation.class;
    }

    @Override
    protected Arbitrary<?> create(final Arbitrary<?> innerArbitrary, final Arbitrary<?> failureArbitrary) {
        return new VavrValidationArbitrary<>(innerArbitrary, failureArbitrary);
    }

}
