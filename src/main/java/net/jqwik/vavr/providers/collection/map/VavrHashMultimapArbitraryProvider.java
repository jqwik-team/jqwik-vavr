package net.jqwik.vavr.providers.collection.map;

import io.vavr.collection.HashMultimap;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.map.VavrHashMultimapArbitrary;
import net.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;

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
