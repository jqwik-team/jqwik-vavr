package net.jqwik.vavr.arbitraries.collection;

import net.jqwik.vavr.arbitraries.base.ListBasedArbitrary;
import io.vavr.collection.Iterator;
import net.jqwik.api.Arbitrary;

public class VavrIteratorArbitrary<T> extends ListBasedArbitrary<T, Iterator<T>> {

    public VavrIteratorArbitrary(final Arbitrary<T> elementArbitrary) {
        super(elementArbitrary);
    }

    @Override
    protected Iterator<T> convertJavaListToVavrCollection(final java.util.List<T> javaList) {
        return Iterator.ofAll(javaList);
    }

}
