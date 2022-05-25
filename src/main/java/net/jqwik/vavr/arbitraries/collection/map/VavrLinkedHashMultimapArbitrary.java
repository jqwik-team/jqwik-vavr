package net.jqwik.vavr.arbitraries.collection.map;

import net.jqwik.vavr.arbitraries.base.MapBasedArbitrary;
import io.vavr.Tuple2;
import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;

/**
 * @author Benno Müller
 */
public class VavrLinkedHashMultimapArbitrary<K, V> extends MapBasedArbitrary<K, V, LinkedHashMultimap<K, V>> {

    public VavrLinkedHashMultimapArbitrary(final Arbitrary<K> keysArbitrary, final Arbitrary<V> valuesArbitrary) {
        super(keysArbitrary, valuesArbitrary);
    }

    @Override
    protected LinkedHashMultimap<K, V> convertTupleListToVavrMap(final List<Tuple2<K, V>> tuple2List) {
        return LinkedHashMultimap.withSeq().ofEntries(tuple2List);
    }

}