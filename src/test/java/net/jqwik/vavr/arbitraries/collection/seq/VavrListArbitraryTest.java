package net.jqwik.vavr.arbitraries.collection.seq;

import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;

import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;

class VavrListArbitraryTest extends VavrTraversableArbitraryTestBase<List<Integer>, List<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends List<Integer>> createCollectionArbitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.list(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll @UniqueElements final List<Integer> list) {
        assertThat(list.distinct().size(), is(list.size()));
    }

    @Property
    void generateDistinctByEvenOdd(@ForAll @UniqueElements(by = ByEvenOdd.class) final List<Integer> list) {
        assertThat(list.size(), lessThanOrEqualTo(2));
        if (list.size() == 2) {
            assertThat(list.get(0) % 2, not(list.get(1) % 2));
        }
    }

    private static class ByEvenOdd implements Function<Integer, Object> {
        @Override
        public Boolean apply(Integer o) {
            return o % 2 == 0;
        }
    }
}
