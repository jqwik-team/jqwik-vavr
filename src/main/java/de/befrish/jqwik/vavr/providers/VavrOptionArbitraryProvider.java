package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.VavrOptionArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;
import io.vavr.control.Option;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrOptionArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Option.class;
    }

    @Override
    protected VavrOptionArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrOptionArbitrary<>(innerArbitrary.injectNull(0.05));
    }

}
