package net.jqwik.vavr.arbitraries.collection.map;

import net.jqwik.vavr.NaturalComparator;
import net.jqwik.vavr.arbitraries.base.MapBasedArbitrary;
import io.vavr.collection.TreeMap;
import net.jqwik.api.Arbitrary;

import java.util.Comparator;
import java.util.Map;

/**
 * @author Benno Müller
 */
public class VavrTreeMapArbitrary<K, V> extends MapBasedArbitrary<K, V, TreeMap<K, V>> {

    private final Comparator<K> keyComparator;

    public VavrTreeMapArbitrary(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary,
            final Comparator<K> keyComparator) {
        super(keysArbitrary, valuesArbitrary);
        this.keyComparator = keyComparator;
    }

    public VavrTreeMapArbitrary(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary) {
        this(keysArbitrary, valuesArbitrary, NaturalComparator.instance());
    }

    @Override
    protected TreeMap<K, V> convertJavaMapToVavrMap(Map<K, V> javaMap) {
        return TreeMap.ofAll(this.keyComparator, javaMap);
    }
}
