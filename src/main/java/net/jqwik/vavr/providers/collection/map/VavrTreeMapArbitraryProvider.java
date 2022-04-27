package net.jqwik.vavr.providers.collection.map;

import io.vavr.collection.TreeMap;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.map.VavrTreeMapArbitrary;
import net.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;

/**
 * @author Benno Müller
 */
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
