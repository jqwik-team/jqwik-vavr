package net.jqwik.vavr.arbitraries.collection.map;

import net.jqwik.vavr.arbitraries.base.MapBasedArbitrary;
import io.vavr.Tuple2;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;

/**
 * @author Benno Müller
 */
public class VavrHashMultimapArbitrary<K, V> extends MapBasedArbitrary<K, V, HashMultimap<K, V>> {

    public VavrHashMultimapArbitrary(final Arbitrary<K> keysArbitrary, final Arbitrary<V> valuesArbitrary) {
        super(keysArbitrary, valuesArbitrary);
    }

    @Override
    protected HashMultimap<K, V> convertTupleListToVavrMap(final List<Tuple2<K, V>> tuple2List) {
        return HashMultimap.withSeq().ofEntries(tuple2List);
    }

}
