package net.jqwik.vavr.arbitraries.collection.map;

import net.jqwik.vavr.NaturalComparator;
import net.jqwik.vavr.arbitraries.base.MapBasedArbitrary;
import io.vavr.collection.TreeMultimap;
import net.jqwik.api.Arbitrary;

import java.util.Comparator;
import java.util.Map;

/**
 * @author Benno Müller
 */
public class VavrTreeMultimapArbitrary<K, V> extends MapBasedArbitrary<K, V, TreeMultimap<K, V>> {

    private final Comparator<K> keyComparator;

    public VavrTreeMultimapArbitrary(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary,
            final Comparator<K> keyComparator) {
        super(keysArbitrary, valuesArbitrary);
        this.keyComparator = keyComparator;
    }

    public VavrTreeMultimapArbitrary(final Arbitrary<K> keysArbitrary, final Arbitrary<V> valuesArbitrary) {
        this(keysArbitrary, valuesArbitrary, NaturalComparator.instance());
    }

    @Override
    protected TreeMultimap<K, V> convertJavaMapToVavrMap(Map<K, V> javaMap) {
        return TreeMultimap.withSeq().ofAll(this.keyComparator, javaMap);
    }
}
