package de.befrish.jqwik.vavr.providers.collection.map;

import de.befrish.jqwik.vavr.arbitraries.collection.map.VavrLinkedHashMapArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;
import io.vavr.collection.LinkedHashMap;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

/**
 * @author Benno Müller
 */
@MetaInfServices(ArbitraryProvider.class)
public class VavrLinkedHashMapArbitraryProvider extends AbstractDoubleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return LinkedHashMap.class;
    }

    @Override
    protected Arbitrary<?> create(final Arbitrary<?> keysArbitrary, final Arbitrary<?> valuesArbitrary) {
        return new VavrLinkedHashMapArbitrary<>(keysArbitrary, valuesArbitrary);
    }

}
