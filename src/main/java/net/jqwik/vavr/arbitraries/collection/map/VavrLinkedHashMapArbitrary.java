package net.jqwik.vavr.arbitraries.collection.map;

import java.util.Map;

import net.jqwik.vavr.arbitraries.base.MapBasedArbitrary;
import io.vavr.collection.LinkedHashMap;
import net.jqwik.api.Arbitrary;

/**
 * @author Benno Müller
 */
public class VavrLinkedHashMapArbitrary<K, V> extends MapBasedArbitrary<K, V, LinkedHashMap<K, V>> {

    public VavrLinkedHashMapArbitrary(final Arbitrary<K> keysArbitrary, final Arbitrary<V> valuesArbitrary) {
        super(keysArbitrary, valuesArbitrary);
    }

    @Override
    protected LinkedHashMap<K, V> convertJavaMapToVavrMap(Map<K, V> javaMap) {
        return LinkedHashMap.ofAll(javaMap);
    }
}
