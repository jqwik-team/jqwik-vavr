package de.befrish.jqwik.vavr.arbitraries.collection.map;

import de.befrish.jqwik.vavr.NaturalComparator;
import de.befrish.jqwik.vavr.arbitraries.base.AbstractMapBasedArbitrary;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.TreeMultimap;
import net.jqwik.api.Arbitrary;

import java.util.Comparator;

/**
 * @author Benno Müller
 */
public class VavrTreeMultimapArbitrary<K, V> extends AbstractMapBasedArbitrary<K, V, TreeMultimap<K, V>> {

    private Comparator<K> keyComparator;

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
    protected TreeMultimap<K, V> convertTupleListToVavrMap(final List<Tuple2<K, V>> tuple2List) {
        return TreeMultimap.withSeq().ofEntries(this.keyComparator, tuple2List);
    }

}
