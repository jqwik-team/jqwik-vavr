package net.jqwik.vavr.arbitraries.collection.map;

import net.jqwik.vavr.NaturalComparator;
import net.jqwik.vavr.arbitraries.base.MapBasedArbitrary;
import io.vavr.Tuple2;
import io.vavr.collection.TreeMap;
import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;

import java.util.Comparator;

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
    protected TreeMap<K, V> convertTupleListToVavrMap(final List<Tuple2<K, V>> tuple2List) {
        return TreeMap.ofEntries(this.keyComparator, tuple2List);
    }

}
