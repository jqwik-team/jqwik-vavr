package de.befrish.jqwik.vavr.arbitraries.collection.map;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractMapBasedArbitrary;
import io.vavr.Tuple2;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;

/**
 * @author Benno Müller
 */
public class VavrHashMultimapArbitrary<K, V> extends AbstractMapBasedArbitrary<K, V, HashMultimap<K, V>> {

    public VavrHashMultimapArbitrary(final Arbitrary<K> keysArbitrary, final Arbitrary<V> valuesArbitrary) {
        super(keysArbitrary, valuesArbitrary);
    }

    @Override
    protected HashMultimap<K, V> convertTupleListToVavrMap(final List<Tuple2<K, V>> tuple2List) {
        return HashMultimap.withSeq().ofEntries(tuple2List);
    }

}
