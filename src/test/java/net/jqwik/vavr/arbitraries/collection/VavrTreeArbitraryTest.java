package net.jqwik.vavr.arbitraries.collection;

import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.vavr.api.VavrArbitraries;
import net.jqwik.vavr.arbitraries.base.VavrTraversableArbitraryTestBase;
import io.vavr.collection.Tree;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.arbitraries.StreamableArbitrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VavrTreeArbitraryTest extends VavrTraversableArbitraryTestBase<Tree<Integer>, Tree<Boolean>> {

    @Override
    protected StreamableArbitrary<Integer, ? extends Tree<Integer>> createCollectionArbtitrary(
            final Arbitrary<Integer> elementArbitrary) {
        return VavrArbitraries.tree(elementArbitrary);
    }

    @Property
    void generateDistinct(@ForAll @UniqueElements final Tree<Integer> tree) {
        assertThat(tree.distinct().size(), is(tree.size()));
    }

}
