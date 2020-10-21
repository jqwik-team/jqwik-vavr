package de.befrish.jqwik.vavr.collection.seq;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.Unique;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class VavrListArbitraryTest extends VavrTraversableArbitraryTestBase<List<Integer>, List<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends List<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.list(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final List<@Unique Integer> list) {
        assertThat(list.distinct().size(), is(list.size()));
    }

}
