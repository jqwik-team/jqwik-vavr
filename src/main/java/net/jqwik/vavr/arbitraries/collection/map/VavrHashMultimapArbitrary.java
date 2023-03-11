package net.jqwik.vavr.arbitraries.collection.map;

import java.util.Map;

import net.jqwik.vavr.arbitraries.base.MapBasedArbitrary;
import io.vavr.collection.HashMultimap;
import net.jqwik.api.Arbitrary;

/**
 * @author Benno Müller
 */
public class VavrHashMultimapArbitrary<K, V> extends MapBasedArbitrary<K, V, HashMultimap<K, V>> {

    public VavrHashMultimapArbitrary(final Arbitrary<K> keysArbitrary, final Arbitrary<V> valuesArbitrary) {
        super(keysArbitrary, valuesArbitrary);
    }

    @Override
    protected HashMultimap<K, V> convertJavaMapToVavrMap(Map<K, V> javaMap) {
        return HashMultimap.withSeq().ofAll(javaMap);
    }
}
