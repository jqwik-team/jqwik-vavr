package net.jqwik.vavr.providers.collection.map;

import io.vavr.collection.LinkedHashMap;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.map.VavrLinkedHashMapArbitrary;
import net.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;

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
