package de.befrish.jqwik.vavr.arbitraries;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractListBasedVavrArbitrary;
import io.vavr.collection.Stream;
import net.jqwik.api.Arbitrary;

import java.util.List;

public class VavrStreamArbitrary<T> extends AbstractListBasedVavrArbitrary<T, Stream<T>> {

    public VavrStreamArbitrary(final Arbitrary<T> elementArbitrary, final boolean elementsUnique) {
        super(elementArbitrary, elementsUnique);
    }

    @Override
    protected Stream<T> convertJavaListToVavrCollection(List<T> javaList) {
        return Stream.ofAll(javaList);
    }

}
