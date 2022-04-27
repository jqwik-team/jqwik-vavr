package net.jqwik.vavr.providers.collection.map;

import io.vavr.collection.TreeMultimap;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.map.VavrTreeMultimapArbitrary;
import net.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;

public class VavrTreeMultimapArbitraryProvider extends AbstractDoubleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return TreeMultimap.class;
    }

    @Override
    protected Arbitrary<?> create(final Arbitrary<?> keysArbitrary, final Arbitrary<?> valuesArbitrary) {
        return new VavrTreeMultimapArbitrary<>(keysArbitrary, valuesArbitrary);
    }

    @Override
    public int priority() {
        return 1; //  provider for SortedMultimap
    }

}
