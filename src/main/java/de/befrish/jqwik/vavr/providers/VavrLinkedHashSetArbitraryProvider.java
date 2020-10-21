package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.VavrLinkedHashSetArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;
import io.vavr.collection.LinkedHashSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrLinkedHashSetArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return LinkedHashSet.class;
    }

    @Override
    protected VavrLinkedHashSetArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrLinkedHashSetArbitrary<>(innerArbitrary);
    }

}
