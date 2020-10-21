package de.befrish.jqwik.vavr.collection.queue;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.Queue;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.Unique;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrQueueArbitraryTest extends VavrTraversableArbitraryTestBase<Queue<Integer>, Queue<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Queue<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.queue(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final Queue<@Unique Integer> queue) {
        assertThat(queue.distinct().size(), is(queue.size()));
    }

}
