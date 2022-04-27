package net.jqwik.vavr.providers.base;

import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

/**
 * @author Benno Müller
 */
public abstract class AbstractArbitraryProvider implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isAssignableFrom(getProvidedType());
    }

    protected abstract Class<?> getProvidedType();

}
