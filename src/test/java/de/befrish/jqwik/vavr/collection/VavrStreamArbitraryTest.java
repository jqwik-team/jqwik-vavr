package de.befrish.jqwik.vavr.collection;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.Stream;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.Unique;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class VavrStreamArbitraryTest extends VavrTraversableArbitraryTestBase<Stream<Integer>, Stream<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Stream<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.stream(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final Stream<@Unique Integer> stream) {
        assertThat(stream.distinct().size(), is(stream.size()));
    }

}
