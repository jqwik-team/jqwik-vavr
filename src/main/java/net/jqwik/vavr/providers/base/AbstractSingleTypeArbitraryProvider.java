package net.jqwik.vavr.providers.base;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.TypeUsage;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * see {@code net.jqwik.engine.providers.AbstractCollectionArbitraryProvider}
 *
 * @author Benno Müller
 */
public abstract class AbstractSingleTypeArbitraryProvider extends AbstractArbitraryProvider {

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        final TypeUsage innerType = targetType.getTypeArgument(0);
        final Set<Arbitrary<?>> innerArbitraries = subtypeProvider.apply(innerType);
        return innerArbitraries.stream()
                .map(this::create)
                .collect(Collectors.toSet());
    }

    protected abstract Arbitrary<?> create(Arbitrary<?> innerArbitrary);

}
