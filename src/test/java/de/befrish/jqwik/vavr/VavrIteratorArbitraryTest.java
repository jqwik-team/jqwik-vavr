package de.befrish.jqwik.vavr;

import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.Iterator;
import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.Unique;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class VavrIteratorArbitraryTest extends VavrTraversableArbitraryTestBase<Iterator<Integer>, Iterator<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Iterator<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.iterator(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final Iterator<@Unique Integer> iterator) {
        final List<Integer> list = iterator.toList(); // iterator can only be iterated once
        assertThat(list.distinct().size(), is(list.size()));
    }

}
