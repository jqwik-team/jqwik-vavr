package net.jqwik.vavr.arbitraries.collection.map;

import net.jqwik.vavr.arbitraries.base.MapBasedArbitrary;
import io.vavr.Tuple2;
import io.vavr.collection.LinkedHashMap;
import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;

/**
 * @author Benno Müller
 */
public class VavrLinkedHashMapArbitrary<K, V> extends MapBasedArbitrary<K, V, LinkedHashMap<K, V>> {

    public VavrLinkedHashMapArbitrary(final Arbitrary<K> keysArbitrary, final Arbitrary<V> valuesArbitrary) {
        super(keysArbitrary, valuesArbitrary);
    }

    @Override
    protected LinkedHashMap<K, V> convertTupleListToVavrMap(final List<Tuple2<K, V>> tuple2List) {
        return LinkedHashMap.ofEntries(tuple2List);
    }

}
