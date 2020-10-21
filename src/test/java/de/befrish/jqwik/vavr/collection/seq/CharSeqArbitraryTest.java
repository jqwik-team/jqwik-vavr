package de.befrish.jqwik.vavr.collection.seq;

import de.befrish.jqwik.vavr.VavrArbitraries;
import io.vavr.collection.CharSeq;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCasesMode;
import net.jqwik.api.ForAll;
import net.jqwik.api.From;
import net.jqwik.api.GenerationMode;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.ShrinkingMode;
import net.jqwik.api.constraints.NotEmpty;
import net.jqwik.api.constraints.Size;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

class CharSeqArbitraryTest {



    /*
     * use {@link de.befrish.jqwik.vavr.VavrArbitraries} factory methods with {@code @Provide} and {@code @From}
     */

    @Provide
    Arbitrary<CharSeq> integersMinSize3() {
        return VavrArbitraries.charSeq(Arbitraries.chars()).ofMinSize(3);
    }

    @Property
    void generateFrom(@ForAll @From("integersMinSize3") final CharSeq charSeq) {
        assertThat(charSeq.size(), is(greaterThanOrEqualTo(3)));
    }

    /*
     * use ArbitraryProvider
     */

    @Property
    void generate(@ForAll final CharSeq charSeq) {
        // Test that the ArbitraryProvider works (fails if not found)
        assertThat(charSeq, is(notNullValue()));
    }

    /*
     * Attribute values of shrinking, generation and edgeCases
     */

    @Property(shrinking = ShrinkingMode.OFF)
    void generateShrinkingOff(@ForAll final CharSeq charSeq) {
        assertThat(charSeq, is(notNullValue()));
    }

    @Property(shrinking = ShrinkingMode.FULL)
    void generateShrinkingFull(@ForAll final CharSeq charSeq) {
        assertThat(charSeq, is(notNullValue()));
    }

    @Property(shrinking = ShrinkingMode.BOUNDED)
    void generateShrinkingBounded(@ForAll final CharSeq charSeq) {
        assertThat(charSeq, is(notNullValue()));
    }

    @Property(generation = GenerationMode.AUTO)
    void generateGenerationAuto(@ForAll final CharSeq charSeq) {
        assertThat(charSeq, is(notNullValue()));
    }

    @Property(generation = GenerationMode.RANDOMIZED)
    void generateGenerationRandomized(@ForAll final CharSeq charSeq) {
        assertThat(charSeq, is(notNullValue()));
    }

    @Property(generation = GenerationMode.EXHAUSTIVE)
    void generateGenerationExhaustive(@ForAll @Size(max = 1) final CharSeq charSeq) {
        assertThat(charSeq.size(), is(lessThanOrEqualTo(1)));
    }

    @Property(edgeCases = EdgeCasesMode.NONE)
    void generateEdgeCasesNone(@ForAll final CharSeq charSeq) {
        assertThat(charSeq, is(notNullValue()));
    }

    @Property(edgeCases = EdgeCasesMode.FIRST)
    void generateEdgeCasesFirst(@ForAll final CharSeq charSeq) {
        assertThat(charSeq, is(notNullValue()));
    }

    @Property(edgeCases = EdgeCasesMode.MIXIN)
    void generateEdgeCasesMixin(@ForAll final CharSeq charSeq) {
        assertThat(charSeq, is(notNullValue()));
    }

    /*
     * StreamableArbitrary
     */

    @Property
    void generateSizable(@ForAll @Size(5) final CharSeq charSeq) {
        assertThat(charSeq.size(), is(5));
    }

    @Property
    void generateNonEmpty(@ForAll @NotEmpty final CharSeq charSeq) {
        assertThat(charSeq, is(not(emptyIterable())));
    }

}
