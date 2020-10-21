package de.befrish.jqwik.vavr.arbitraries.collection;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractListBasedVavrArbitrary;
import io.vavr.collection.Iterator;
import net.jqwik.api.Arbitrary;

public class VavrIteratorArbitrary<T> extends AbstractListBasedVavrArbitrary<T, Iterator<T>> {

    public VavrIteratorArbitrary(final Arbitrary<T> elementArbitrary, final boolean elementsUnique) {
        super(elementArbitrary, elementsUnique);
    }

    @Override
    protected Iterator<T> convertJavaListToVavrCollection(final java.util.List<T> javaList) {
        return Iterator.ofAll(javaList);
    }

}
