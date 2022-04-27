package net.jqwik.vavr.providers.control;

import io.vavr.control.Validation;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.control.VavrValidationArbitrary;
import net.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;

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
