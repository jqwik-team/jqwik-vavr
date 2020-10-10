package de.befrish.jqwik.vavr.arbitraries;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractListBasedVavrArbitrary;
import io.vavr.collection.Tree;
import net.jqwik.api.Arbitrary;

import java.util.List;

public class VavrTreeArbitrary<T> extends AbstractListBasedVavrArbitrary<T, Tree<T>> {

    public VavrTreeArbitrary(final Arbitrary<T> elementArbitrary, final boolean elementsUnique) {
        super(elementArbitrary, elementsUnique);
    }

    @Override
    protected Tree<T> convertJavaListToVavrCollection(final List<T> javaList) {
        return Tree.ofAll(javaList);
    }

}
