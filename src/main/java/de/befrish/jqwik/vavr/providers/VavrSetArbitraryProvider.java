package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.VavrSetArbitrary;
import de.befrish.jqwik.vavr.arbitraries.impl.DefaultVavrSetArbitrary;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

/**
 * @author Benno Müller
 */
@MetaInfServices(ArbitraryProvider.class)
public class VavrSetArbitraryProvider extends AbstractVavrContainerArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Set.class;
    }

    @Override
    protected VavrSetArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new DefaultVavrSetArbitrary<>(innerArbitrary);
    }

}
