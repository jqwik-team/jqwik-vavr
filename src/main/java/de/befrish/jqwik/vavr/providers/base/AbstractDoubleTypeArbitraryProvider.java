package de.befrish.jqwik.vavr.providers.base;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.TypeUsage;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Benno Müller
 */
public abstract class AbstractDoubleTypeArbitraryProvider extends AbstractArbitraryProvider {

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        final TypeUsage firstType = targetType.getTypeArgument(0);
        final TypeUsage secondType = targetType.getTypeArgument(1);
        final Set<Arbitrary<?>> firstArbitraries = subtypeProvider.apply(firstType);
        final Set<Arbitrary<?>> secondArbitraries = subtypeProvider.apply(secondType);
        return firstArbitraries.stream()
                .flatMap(firstArbitrary -> secondArbitraries.stream()
                        .map(secondArbitrary -> create(firstArbitrary, secondArbitrary)))
                .collect(Collectors.toSet());
    }

    protected abstract Arbitrary<?> create(final Arbitrary<?> firstArbitrary, final Arbitrary<?> secondArbitrary);

}
