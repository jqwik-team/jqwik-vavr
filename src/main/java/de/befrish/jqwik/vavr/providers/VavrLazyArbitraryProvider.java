package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.impl.DefaultVavrLazyArbitrary;
import de.befrish.jqwik.vavr.arbitraries.impl.VavrLazyArbitrary;
import io.vavr.Lazy;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

/**
 * @author Benno Müller
 */
@MetaInfServices(ArbitraryProvider.class)
public class VavrLazyArbitraryProvider extends AbstractVavrContainerArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Lazy.class;
    }

    @Override
    protected VavrLazyArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new DefaultVavrLazyArbitrary<>(innerArbitrary);
    }

}
