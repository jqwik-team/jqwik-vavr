package net.jqwik.vavr.arbitraries.collection.set;

//@PropertyDefaults(tries = 50)
class VavrBitSetArbitraryTest {


    /*
     * use {@link net.jqwik.vavr.api.VavrArbitraries} factory methods with {@code @Provide} and {@code @From}
     */

//    @Provide
//    Arbitrary<BitSet<Integer>> integersMinSize3() {
//        return VavrArbitraries.bitSet(Arbitraries.integers().greaterOrEqual(0)).ofMinSize(3);
//    }

//    @Property
//    void generateFrom(@ForAll @From("integersMinSize3") final BitSet<Integer> bitSet) {
//        assertThat(bitSet.size(), is(greaterThanOrEqualTo(3)));
//    }

//    @Provide
//    Arbitrary<BitSet<Integer>> integersEmpty() {
//        return VavrArbitraries.bitSet(Arbitraries.integers().filter(i -> i >= 0)).filter(BitSet::isEmpty);
//    }

//    @Property
//    void generateFromEmpty(@ForAll @From("integersEmpty") final BitSet<Integer> bitSet) {
//        assertThat(bitSet, is(emptyIterable()));
//    }

    /*
     * use ArbitraryProvider
     */

//    @Property
//    void generateInteger(@ForAll final BitSet<@IntRange(min = 0) Integer> bitSet) {
//        // Test that the ArbitraryProvider works (fails if not found)
//        assertThat(bitSet, is(notNullValue()));
//    }

//    @Property
//    void generateShort(@ForAll final BitSet<@ShortRange(min = 0) Short> bitSet) {
//        // Test that the ArbitraryProvider works (fails if not found)
//        assertThat(bitSet, is(notNullValue()));
//    }

    // TODO do no work
//    @Property
//    void generateLong(@ForAll final BitSet<@LongRange(min = 0L) Long> bitSet) {
//        // Test that the ArbitraryProvider works (fails if not found)
//        assertThat(bitSet, is(notNullValue()));
//    }

//    @Property
//    void generateBoolean(@ForAll final BitSet<Boolean> bitSet) { // TODO
//        // Test that the ArbitraryProvider works (fails if not found)
//        assertThat(bitSet, is(notNullValue()));
//    }

//    @Property
//    void generateByte(@ForAll final BitSet<@ByteRange(min = 0) Byte> bitSet) {
//        // Test that the ArbitraryProvider works (fails if not found)
//        assertThat(bitSet, is(notNullValue()));
//    }

//    @Property
//    void generateCharacter(@ForAll final BitSet<Character> bitSet) {
//        // Test that the ArbitraryProvider works (fails if not found)
//        assertThat(bitSet, is(notNullValue()));
//    }

//    @Property
//    void generateEnum(@ForAll final BitSet<SampleEnum> bitSet) { // TODO
//        // Test that the ArbitraryProvider works (fails if not found)
//        assertThat(bitSet, is(notNullValue()));
//    }

    /*
     * Attribute values of shrinking, generation and edgeCases
     */

//    @Property(shrinking = ShrinkingMode.OFF)
//    void generateShrinkingOff(@ForAll final BitSet<@Positive Integer> bitSet) {
//        assertThat(bitSet, is(notNullValue()));
//    }

//    @Property(shrinking = ShrinkingMode.FULL)
//    void generateShrinkingFull(@ForAll final BitSet<@Positive Integer> bitSet) {
//        assertThat(bitSet, is(notNullValue()));
//    }

//    @Property(shrinking = ShrinkingMode.BOUNDED)
//    void generateShrinkingBounded(@ForAll final BitSet<@Positive Integer> bitSet) {
//        assertThat(bitSet, is(notNullValue()));
//    }

//    @Property(generation = GenerationMode.AUTO)
//    void generateGenerationAuto(@ForAll final BitSet<@Positive Integer> bitSet) {
//        assertThat(bitSet, is(notNullValue()));
//    }

//    @Property(generation = GenerationMode.RANDOMIZED)
//    void generateGenerationRandomized(@ForAll final BitSet<@Positive Integer> bitSet) {
//        assertThat(bitSet, is(notNullValue()));
//    }

    // TODO BitSet<Boolean> Problem
//    @Property(generation = GenerationMode.EXHAUSTIVE)
//    void generateGenerationExhaustive(@ForAll @Size(max = 5) final BitSet<Boolean> bitSet) {
//        assertThat(bitSet.size(), is(lessThanOrEqualTo(5)));
//    }

//    @Property(edgeCases = EdgeCasesMode.NONE)
//    void generateEdgeCasesNone(@ForAll final BitSet<@Positive Integer> bitSet) {
//        assertThat(bitSet, is(notNullValue()));
//    }

//    @Property(edgeCases = EdgeCasesMode.FIRST)
//    void generateEdgeCasesFirst(@ForAll final BitSet<@Positive Integer> bitSet) {
//        assertThat(bitSet, is(notNullValue()));
//    }

//    @Property(edgeCases = EdgeCasesMode.MIXIN)
//    void generateEdgeCasesMixin(@ForAll final BitSet<@Positive Integer> bitSet) {
//        assertThat(bitSet, is(notNullValue()));
//    }

    /*
     * StreamableArbitrary
     */

//    @Property
//    void generateSizable(@ForAll @Size(5) final BitSet<@Positive Integer> bitSet) {
//        assertThat(bitSet.size(), is(5));
//    }

//    @Property
//    void generateNonEmpty(@ForAll @NotEmpty final BitSet<@Positive Integer> bitSet) {
//        assertThat(bitSet, is(not(emptyIterable())));
//    }

//    @Property
//    void generateDistinct(@ForAll final BitSet<@Positive Integer> bitSet) { // no @Unique because set should be always distinct
//        assertThat(bitSet.distinct().size(), is(bitSet.size()));
//    }

    private enum SampleEnum {
        X, Y, Z
    }

}
