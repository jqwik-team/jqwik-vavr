package net.jqwik.vavr.arbitraries.base;

import io.vavr.Tuple2;
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
import net.jqwik.api.arbitraries.SizableArbitrary;
import net.jqwik.api.constraints.NotEmpty;
import net.jqwik.api.constraints.Size;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public abstract class VavrMapArbitraryTestBase<
        UI extends Traversable<Tuple2<Integer, String>>,
        UB extends Traversable<Tuple2<Boolean, Boolean>>> extends ArbitraryTestBase<UI> {

    /**
     * Should use {@code VavrArbitraries} factory method.
     * @param keysArbitrary
     * @return
     */
    protected abstract SizableArbitrary<? extends UI> createMapArbtitrary(
            Arbitrary<Integer> keysArbitrary,
            Arbitrary<String> valuesArbitrary);

    /*
     * use {@link net.jqwik.vavr.api.VavrArbitraries} factory methods with {@code @Provide} and {@code @From}
     */

    @Provide
    Arbitrary<? extends UI> integersAndStringsMinSize3() {
        return createMapArbtitrary(Arbitraries.integers(), Arbitraries.strings()).ofMinSize(3);
    }

    @Property
    void generateFrom(@ForAll @From("integersAndStringsMinSize3") final UI u) {
        assertThat(u.size(), is(greaterThanOrEqualTo(3)));
    }

    /*
     * Attribute values of shrinking, generation and edgeCases
     */

    @Property(generation = GenerationMode.EXHAUSTIVE)
    void generateGenerationExhaustive(@ForAll @Size(max = 5) final UB u) {
        assertThat(u.size(), is(lessThanOrEqualTo(5)));
    }

    /*
     * SizableArbitrary
     */

    @Property
    void generateSizable(@ForAll @Size(5) final UI u) {
        assertThat(u.size(), is(5));
    }

    @Property
    void generateNonEmpty(@ForAll @NotEmpty final UI u) {
        assertThat(u, is(not(emptyIterable())));
    }

}
