package de.befrish.jqwik.vavr;

import de.befrish.jqwik.vavr.arbitraries.VavrArrayArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrBitSetArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrCharSeqArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrEitherArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrFutureArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrHashSetArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrIteratorArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrLazyArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrLinkedHashSetArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrListArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrOptionArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrPriorityQueueArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrQueueArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrStreamArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrTreeArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrTreeSetArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrTryArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrValidationArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrVectorArbitrary;
import io.vavr.collection.Array;
import io.vavr.collection.BitSet;
import io.vavr.collection.CharSeq;
import io.vavr.collection.HashSet;
import io.vavr.collection.IndexedSeq;
import io.vavr.collection.Iterator;
import io.vavr.collection.LinearSeq;
import io.vavr.collection.LinkedHashSet;
import io.vavr.collection.List;
import io.vavr.collection.PriorityQueue;
import io.vavr.collection.Queue;
import io.vavr.collection.Seq;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import io.vavr.collection.Tree;
import io.vavr.collection.TreeSet;
import io.vavr.collection.Vector;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.arbitraries.StreamableArbitrary;

import java.util.ArrayList;
import java.util.Comparator;

public final class VavrArbitraries {

    private VavrArbitraries() {
        super();
    }

    /*
     * Classes
     */

    public static <T> VavrLazyArbitrary<T> lazy(final Arbitrary<T> innerArbitrary) {
        return new VavrLazyArbitrary<>(innerArbitrary);
    }

    public static <T> VavrOptionArbitrary<T> option(final Arbitrary<T> innerArbitrary) {
        return new VavrOptionArbitrary<>(innerArbitrary.injectNull(0.05));
    }

    public static <L, R> VavrEitherArbitrary<L, R> either(
            final Arbitrary<L> leftArbitrary,
            final Arbitrary<R> rightArbitrary) {
        return new VavrEitherArbitrary<>(leftArbitrary, rightArbitrary);
    }

    public static <E, T> VavrValidationArbitrary<E, T> validation(
            final Arbitrary<E> failureArbitrary,
            final Arbitrary<T> innerArbitrary) {
        return new VavrValidationArbitrary<>(failureArbitrary, innerArbitrary);
    }

    public static <T> VavrTryArbitrary<T> try_(
            final Arbitrary<T> innerArbitrary,
            final Arbitrary<Throwable> exceptionArbitrary) {
        return new VavrTryArbitrary<>(innerArbitrary, exceptionArbitrary);
    }

    public static <T> VavrFutureArbitrary<T> future(
            final Arbitrary<T> innerArbitrary,
            final Arbitrary<Throwable> exceptionArbitrary) {
        return new VavrFutureArbitrary<>(innerArbitrary, exceptionArbitrary);
    }

    public static <T> StreamableArbitrary<T, Iterator<T>> iterator(final Arbitrary<T> elementArbitrary) {
        return new VavrIteratorArbitrary<>(elementArbitrary, elementArbitrary.isUnique());
    }

    public static <T> StreamableArbitrary<T, Stream<T>> stream(final Arbitrary<T> elementArbitrary) {
        return new VavrStreamArbitrary<>(elementArbitrary, elementArbitrary.isUnique());
    }

    public static <T> StreamableArbitrary<T, List<T>> list(final Arbitrary<T> elementArbitrary) {
        return new VavrListArbitrary<>(elementArbitrary, elementArbitrary.isUnique());
    }

    public static <T> StreamableArbitrary<T, Queue<T>> queue(final Arbitrary<T> elementArbitrary) {
        return new VavrQueueArbitrary<>(elementArbitrary, elementArbitrary.isUnique());
    }

    public static <T extends Comparable<T>> StreamableArbitrary<T, PriorityQueue<T>> priorityQueue(
            final Arbitrary<T> elementArbitrary) {
        return new VavrPriorityQueueArbitrary<>(
                elementArbitrary,
                elementArbitrary.isUnique());
    }

    public static <T> StreamableArbitrary<T, PriorityQueue<T>> priorityQueue(
            final Arbitrary<T> elementArbitrary,
            final Comparator<T> comparator) {
        return new VavrPriorityQueueArbitrary<>(
                elementArbitrary,
                elementArbitrary.isUnique(),
                comparator);
    }

    public static <T> StreamableArbitrary<T, Array<T>> array(final Arbitrary<T> elementArbitrary) {
        return new VavrArrayArbitrary<>(elementArbitrary, elementArbitrary.isUnique());
    }

    public static <T> StreamableArbitrary<T, Vector<T>> vector(final Arbitrary<T> elementArbitrary) {
        return new VavrVectorArbitrary<>(elementArbitrary, elementArbitrary.isUnique());
    }

    public static StreamableArbitrary<Character, CharSeq> charSeq(final Arbitrary<Character> elementArbitrary) {
        return new VavrCharSeqArbitrary(elementArbitrary, elementArbitrary.isUnique());
    }

    public static <T> StreamableArbitrary<T, HashSet<T>> hashSet(final Arbitrary<T> elementArbitrary) {
        return new VavrHashSetArbitrary<>(elementArbitrary);
    }

    public static <T> StreamableArbitrary<T, LinkedHashSet<T>> linkedHashSet(final Arbitrary<T> elementArbitrary) {
        return new VavrLinkedHashSetArbitrary<>(elementArbitrary);
    }

    public static <T extends Comparable<T>> StreamableArbitrary<T, TreeSet<T>> treeSet(
            final Arbitrary<T> elementArbitrary) {
        return new VavrTreeSetArbitrary<>(elementArbitrary);
    }

    public static <T> StreamableArbitrary<T, TreeSet<T>> treeSet(
            final Arbitrary<T> elementArbitrary,
            final Comparator<T> comparator) {
        return new VavrTreeSetArbitrary<>(elementArbitrary, comparator);
    }

    public static <T extends Comparable<T>> StreamableArbitrary<T, BitSet<T>> bitSet(
            final Arbitrary<T> elementArbitrary) {
        return new VavrBitSetArbitrary<>(elementArbitrary);
    }

    public static <T> StreamableArbitrary<T, Tree<T>> tree(final Arbitrary<T> elementArbitrary) {
        return new VavrTreeArbitrary<>(elementArbitrary, elementArbitrary.isUnique());
    }

    /*
     * Interfaces
     */

    public static <T> StreamableArbitrary<T, ? extends Seq<T>> seq(final Arbitrary<T> elementArbitrary) {
        return list(elementArbitrary);
    }

    public static <T> StreamableArbitrary<T, ? extends LinearSeq<T>> linearSeq(final Arbitrary<T> elementArbitrary) {
        return list(elementArbitrary);
    }

    public static <T> StreamableArbitrary<T, ? extends IndexedSeq<T>> indexedSeq(final Arbitrary<T> elementArbitrary) {
        return array(elementArbitrary);
    }

    public static <T> StreamableArbitrary<T, ? extends Set<T>> set(final Arbitrary<T> elementArbitrary) {
        return hashSet(elementArbitrary);
    }

    public static <T extends Comparable<T>> StreamableArbitrary<T, TreeSet<T>> sortedSet(
            final Arbitrary<T> elementArbitrary) {
        return treeSet(elementArbitrary);
    }

}
