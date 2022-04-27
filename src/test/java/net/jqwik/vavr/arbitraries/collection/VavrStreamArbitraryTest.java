package net.jqwik.vavr.arbitraries.collection;

import io.vavr.collection.Stream;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrStreamArbitraryTest extends VavrTraversableArbitraryTestBase<Stream<Integer>, Stream<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Stream<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.stream(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll @UniqueElements final Stream<Integer> stream) {
        assertThat(stream.distinct().size(), is(stream.size()));
    }

}
