package net.jqwik.vavr.arbitraries.collection;

import io.vavr.collection.List;
import io.vavr.collection.Traversable;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrTraversableArbitraryTest extends VavrTraversableArbitraryTestBase<Traversable<Integer>, Traversable<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Traversable<Integer>> createCollectionArbitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.iterator(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll @UniqueElements final Traversable<Integer> traversable) {
        final List<Integer> list = traversable.toList(); // default type iterator can only be iterated once
        assertThat(list.distinct().size(), is(list.size()));
    }

}
