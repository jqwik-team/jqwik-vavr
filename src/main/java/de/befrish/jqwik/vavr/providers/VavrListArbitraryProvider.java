package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.VavrListArbitrary;
import de.befrish.jqwik.vavr.arbitraries.impl.DefaultVavrListArbitrary;
import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

/**
 * @author Benno Müller
 */
@MetaInfServices(ArbitraryProvider.class)
public class VavrListArbitraryProvider extends AbstractVavrContainerArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return List.class;
    }

    @Override
    protected VavrListArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new DefaultVavrListArbitrary<>(innerArbitrary, innerArbitrary.isUnique());
    }

}
