package net.jqwik.vavr.arbitraries.collection;

import net.jqwik.vavr.arbitraries.base.ListBasedArbitrary;
import io.vavr.collection.Stream;
import net.jqwik.api.Arbitrary;

import java.util.List;

public class VavrStreamArbitrary<T> extends ListBasedArbitrary<T, Stream<T>> {

    public VavrStreamArbitrary(final Arbitrary<T> elementArbitrary) {
        super(elementArbitrary);
    }

    @Override
    protected Stream<T> convertJavaListToVavrCollection(final List<T> javaList) {
        return Stream.ofAll(javaList);
    }

}
