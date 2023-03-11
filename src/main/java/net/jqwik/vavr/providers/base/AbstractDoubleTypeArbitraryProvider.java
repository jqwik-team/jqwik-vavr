package net.jqwik.vavr.providers.base;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.TypeUsage;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * In analogy to jqwik's implementation of net.jqwik.engine.providers.HashMapArbitraryProvider
 *
 * @author Benno Müller
 */
public abstract class AbstractDoubleTypeArbitraryProvider extends AbstractArbitraryProvider {

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        final TypeUsage firstType = targetType.getTypeArgument(0);
        final TypeUsage secondType = targetType.getTypeArgument(1);

        return subtypeProvider
                .resolveAndCombine(firstType, secondType)
                .map(arbitraries -> {
                    final Arbitrary<?> firstArbitrary = arbitraries.get(0);
                    final Arbitrary<?> secondArbitrary = arbitraries.get(1);
                    return create(firstArbitrary, secondArbitrary);
                })
                .collect(Collectors.toSet());
    }

    protected abstract Arbitrary<?> create(final Arbitrary<?> firstArbitrary, final Arbitrary<?> secondArbitrary);

}
