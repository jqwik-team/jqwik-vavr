package net.jqwik.vavr.arbitraries.base;

import net.jqwik.api.EdgeCasesMode;
import net.jqwik.api.ForAll;
import net.jqwik.api.GenerationMode;
import net.jqwik.api.Property;
import net.jqwik.api.ShrinkingMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public abstract class ArbitraryTestBase<UI> {

    /*
     * use ArbitraryProvider
     */

    @Property
    void generate(@ForAll final UI u) {
        // Test that the ArbitraryProvider works (fails if not found)
        assertThat(u, is(notNullValue()));
    }

    /*
     * Attribute values of shrinking, generation and edgeCases
     */

    @Property(shrinking = ShrinkingMode.OFF)
    void generateShrinkingOff(@ForAll final UI u) {
        assertThat(u, is(notNullValue()));
    }

    @Property(shrinking = ShrinkingMode.FULL)
    void generateShrinkingFull(@ForAll final UI u) {
        assertThat(u, is(notNullValue()));
    }

    @Property(shrinking = ShrinkingMode.BOUNDED)
    void generateShrinkingBounded(@ForAll final UI u) {
        assertThat(u, is(notNullValue()));
    }

    @Property(generation = GenerationMode.AUTO)
    void generateGenerationAuto(@ForAll final UI u) {
        assertThat(u, is(notNullValue()));
    }

    @Property(generation = GenerationMode.RANDOMIZED)
    void generateGenerationRandomized(@ForAll final UI u) {
        assertThat(u, is(notNullValue()));
    }

    @Property(edgeCases = EdgeCasesMode.NONE)
    void generateEdgeCasesNone(@ForAll final UI u) {
        assertThat(u, is(notNullValue()));
    }

    @Property(edgeCases = EdgeCasesMode.FIRST)
    void generateEdgeCasesFirst(@ForAll final UI u) {
        assertThat(u, is(notNullValue()));
    }

    @Property(edgeCases = EdgeCasesMode.MIXIN)
    void generateEdgeCasesMixin(@ForAll final UI u) {
        assertThat(u, is(notNullValue()));
    }

}
