package net.jqwik.vavr.arbitraries.collection;

import net.jqwik.vavr.arbitraries.base.ListBasedVavrArbitrary;
import io.vavr.collection.Tree;
import net.jqwik.api.Arbitrary;

import java.util.List;

public class VavrTreeArbitrary<T> extends ListBasedVavrArbitrary<T, Tree<T>> {

    public VavrTreeArbitrary(final Arbitrary<T> elementArbitrary) {
        super(elementArbitrary);
    }

    @Override
    protected Tree<T> convertJavaListToVavrCollection(final List<T> javaList) {
        return Tree.ofAll(javaList);
    }

}
