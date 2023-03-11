package net.jqwik.vavr.arbitraries.collection.map;

import java.util.Map;

import net.jqwik.vavr.arbitraries.base.MapBasedArbitrary;
import io.vavr.collection.LinkedHashMultimap;
import net.jqwik.api.Arbitrary;

/**
 * @author Benno Müller
 */
public class VavrLinkedHashMultimapArbitrary<K, V> extends MapBasedArbitrary<K, V, LinkedHashMultimap<K, V>> {

    public VavrLinkedHashMultimapArbitrary(final Arbitrary<K> keysArbitrary, final Arbitrary<V> valuesArbitrary) {
        super(keysArbitrary, valuesArbitrary);
    }

    @Override
    protected LinkedHashMultimap<K, V> convertJavaMapToVavrMap(Map<K, V> javaMap) {
        return LinkedHashMultimap.withSeq().ofAll(javaMap);
    }
}
