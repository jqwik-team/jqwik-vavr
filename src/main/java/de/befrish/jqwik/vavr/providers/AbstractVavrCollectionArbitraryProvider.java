package de.befrish.jqwik.vavr.providers;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @see net.jqwik.engine.providers.AbstractCollectionArbitraryProvider
 *
 * @author Benno Müller
 */
abstract class AbstractVavrCollectionArbitraryProvider implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isAssignableFrom(getProvidedType());
    }

    protected abstract Class<?> getProvidedType();

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        final TypeUsage elementType = targetType.getTypeArgument(0);
        final Set<Arbitrary<?>> elementArbitraries = subtypeProvider.apply(elementType);
        return elementArbitraries.stream()
                .map(this::create)
                .collect(Collectors.toSet());
    }

    protected abstract Arbitrary<?> create(Arbitrary<?> innerArbitrary);

}
