package de.befrish.jqwik.vavr.providers.collection.map;

import de.befrish.jqwik.vavr.arbitraries.collection.map.VavrTreeMapArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;
import io.vavr.collection.TreeMap;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

/**
 * @author Benno Müller
 */
@MetaInfServices(ArbitraryProvider.class)
public class VavrTreeMapArbitraryProvider extends AbstractDoubleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return TreeMap.class;
    }

    @Override
    protected Arbitrary<?> create(final Arbitrary<?> keysArbitrary, final Arbitrary<?> valuesArbitrary) {
        return new VavrTreeMapArbitrary<>(keysArbitrary, valuesArbitrary);
    }

    @Override
    public int priority() {
        return 1; //  provider for SortedMap
    }

}
