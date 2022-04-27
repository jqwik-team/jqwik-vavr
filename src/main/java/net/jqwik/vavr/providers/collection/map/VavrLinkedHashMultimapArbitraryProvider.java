package net.jqwik.vavr.providers.collection.map;

import io.vavr.collection.LinkedHashMultimap;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.map.VavrLinkedHashMultimapArbitrary;
import net.jqwik.vavr.providers.base.AbstractDoubleTypeArbitraryProvider;

public class VavrLinkedHashMultimapArbitraryProvider extends AbstractDoubleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return LinkedHashMultimap.class;
    }

    @Override
    protected Arbitrary<?> create(final Arbitrary<?> keysArbitrary, final Arbitrary<?> valuesArbitrary) {
        return new VavrLinkedHashMultimapArbitrary<>(keysArbitrary, valuesArbitrary);
    }

}
