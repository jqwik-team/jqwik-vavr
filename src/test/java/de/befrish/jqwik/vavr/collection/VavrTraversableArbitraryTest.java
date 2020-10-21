package de.befrish.jqwik.vavr.collection;

import de.befrish.jqwik.vavr.VavrArbitraries;
import de.befrish.jqwik.vavr.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.CharSeq;
import io.vavr.collection.List;
import io.vavr.collection.Traversable;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCasesMode;
import net.jqwik.api.ForAll;
import net.jqwik.api.From;
import net.jqwik.api.GenerationMode;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.ShrinkingMode;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.constraints.NotEmpty;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.Unique;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

class VavrTraversableArbitraryTest extends VavrTraversableArbitraryTestBase<Traversable<Integer>, Traversable<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Traversable<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.iterator(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll final Traversable<@Unique Integer> traversable) {
        final List<Integer> list = traversable.toList(); // default type iterator can only be iterated once
        assertThat(list.distinct().size(), is(list.size()));
    }

}
