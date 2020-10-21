package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.VavrFutureArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrTryArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractArbitraryProvider;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;
import org.kohsuke.MetaInfServices;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@MetaInfServices(ArbitraryProvider.class)
public class VavrFutureArbitraryProvider extends AbstractArbitraryProvider {

    private static final List<Throwable> DEFAULT_EXCEPTIONS = List.of(
            new Throwable(),
            new Exception(),
            new RuntimeException()
    );

    @Override
    protected Class<?> getProvidedType() {
        return Future.class;
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        final TypeUsage innerType = targetType.getTypeArgument(0);
        final Set<Arbitrary<?>> innerArbitraries = subtypeProvider.apply(innerType);
        final Set<Arbitrary<Throwable>> exceptionArbitraries = Collections.singleton(
                Arbitraries.of(DEFAULT_EXCEPTIONS.toJavaArray(Throwable[]::new)));
        return innerArbitraries.stream()
                .flatMap(firstArbitrary -> exceptionArbitraries.stream()
                        .map(exceptionArbitrary -> create(firstArbitrary, exceptionArbitrary)))
                .collect(Collectors.toSet());
    }

    private Arbitrary<?> create(final Arbitrary<?> innerArbitrary, final Arbitrary<Throwable> exceptionArbitrary) {
        return new VavrFutureArbitrary<>(innerArbitrary, exceptionArbitrary);
    }

}
