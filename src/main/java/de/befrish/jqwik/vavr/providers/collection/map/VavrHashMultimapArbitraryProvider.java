package de.befrish.jqwik.vavr.providers.collection.map;

import de.befrish.jqwik.vavr.arbitraries.collection.map.VavrHashMultimapArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;
import io.vavr.collection.HashMultimap;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

/**
 * @author Benno Müller
 */
@MetaInfServices(ArbitraryProvider.class)
public class VavrHashMultimapArbitraryProvider extends AbstractDoubleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return HashMultimap.class;
    }

    @Override
    protected Arbitrary<?> create(final Arbitrary<?> keysArbitrary, final Arbitrary<?> valuesArbitrary) {
        return new VavrHashMultimapArbitrary<>(keysArbitrary, valuesArbitrary);
    }

    @Override
    public int priority() {
        return 2; //  provider for Multimap
    }

}
