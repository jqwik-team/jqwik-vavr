package net.jqwik.vavr.arbitraries.collection;

import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.Iterator;
import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrIteratorArbitraryTest extends VavrTraversableArbitraryTestBase<Iterator<Integer>, Iterator<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Iterator<Integer>> createCollectionArbitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.iterator(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll @UniqueElements final Iterator<Integer> iterator) {
        final List<Integer> list = iterator.toList(); // iterator can only be iterated once
        assertThat(list.distinct().size(), is(list.size()));
    }

}
