package net.jqwik.vavr.arbitraries.collection.map;

import java.util.Map;

import net.jqwik.vavr.arbitraries.base.MapBasedArbitrary;
import io.vavr.collection.HashMap;
import net.jqwik.api.Arbitrary;

/**
 * @author Benno Müller
 */
public class VavrHashMapArbitrary<K, V> extends MapBasedArbitrary<K, V, HashMap<K, V>> {

    public VavrHashMapArbitrary(final Arbitrary<K> keysArbitrary, final Arbitrary<V> valuesArbitrary) {
        super(keysArbitrary, valuesArbitrary);
    }

    @Override
    protected HashMap<K, V> convertJavaMapToVavrMap(Map<K, V> javaMap) {
        return HashMap.ofAll(javaMap);
    }
}
