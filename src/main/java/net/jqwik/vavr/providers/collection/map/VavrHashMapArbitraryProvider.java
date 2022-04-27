package net.jqwik.vavr.providers.collection.map;

import io.vavr.collection.HashMap;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.map.VavrHashMapArbitrary;
import net.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;

public class VavrHashMapArbitraryProvider extends AbstractDoubleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return HashMap.class;
    }

    @Override
    protected Arbitrary<?> create(final Arbitrary<?> keysArbitrary, final Arbitrary<?> valuesArbitrary) {
        return new VavrHashMapArbitrary<>(keysArbitrary, valuesArbitrary);
    }

    @Override
    public int priority() {
        return 2; //  provider for Map
    }

}
