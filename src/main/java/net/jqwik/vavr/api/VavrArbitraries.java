package net.jqwik.vavr.api;

import net.jqwik.vavr.arbitraries.collection.map.VavrHashMultimapArbitrary;
import net.jqwik.vavr.arbitraries.collection.map.VavrLinkedHashMultimapArbitrary;
import net.jqwik.vavr.arbitraries.collection.map.VavrTreeMultimapArbitrary;
import net.jqwik.vavr.arbitraries.collection.seq.VavrArrayArbitrary;
import net.jqwik.vavr.arbitraries.collection.set.VavrBitSetArbitrary;
import net.jqwik.vavr.arbitraries.collection.seq.VavrCharSeqArbitrary;
import net.jqwik.vavr.arbitraries.control.VavrEitherArbitrary;
import net.jqwik.vavr.arbitraries.concurrent.VavrFutureArbitrary;
import net.jqwik.vavr.arbitraries.collection.map.VavrHashMapArbitrary;
import net.jqwik.vavr.arbitraries.collection.set.VavrHashSetArbitrary;
import net.jqwik.vavr.arbitraries.collection.VavrIteratorArbitrary;
import net.jqwik.vavr.arbitraries.VavrLazyArbitrary;
import net.jqwik.vavr.arbitraries.collection.map.VavrLinkedHashMapArbitrary;
import net.jqwik.vavr.arbitraries.collection.set.VavrLinkedHashSetArbitrary;
import net.jqwik.vavr.arbitraries.collection.seq.VavrListArbitrary;
import net.jqwik.vavr.arbitraries.control.VavrOptionArbitrary;
import net.jqwik.vavr.arbitraries.collection.queue.VavrPriorityQueueArbitrary;
import net.jqwik.vavr.arbitraries.collection.queue.VavrQueueArbitrary;
import net.jqwik.vavr.arbitraries.collection.VavrStreamArbitrary;
import net.jqwik.vavr.arbitraries.collection.VavrTreeArbitrary;
import net.jqwik.vavr.arbitraries.collection.map.VavrTreeMapArbitrary;
import net.jqwik.vavr.arbitraries.collection.set.VavrTreeSetArbitrary;
import net.jqwik.vavr.arbitraries.control.VavrTryArbitrary;
import net.jqwik.vavr.arbitraries.control.VavrValidationArbitrary;
import net.jqwik.vavr.arbitraries.collection.seq.VavrVectorArbitrary;
import io.vavr.collection.Array;
import io.vavr.collection.BitSet;
import io.vavr.collection.CharSeq;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.HashSet;
import io.vavr.collection.IndexedSeq;
import io.vavr.collection.Iterator;
import io.vavr.collection.LinearSeq;
import io.vavr.collection.LinkedHashMap;
import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.LinkedHashSet;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Multimap;
import io.vavr.collection.PriorityQueue;
import io.vavr.collection.Queue;
import io.vavr.collection.Seq;
import io.vavr.collection.Set;
import io.vavr.collection.SortedMap;
import io.vavr.collection.SortedMultimap;
import io.vavr.collection.Stream;
import io.vavr.collection.Tree;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeMultimap;
import io.vavr.collection.TreeSet;
import io.vavr.collection.Vector;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.arbitraries.SizableArbitrary;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import org.apiguardian.api.API;

import java.util.Comparator;

import static org.apiguardian.api.API.Status.STABLE;

@API(status = STABLE, since = "2.0.0")
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
        return new VavrIteratorArbitrary<>(elementArbitrary);
    }

    public static <T> StreamableArbitrary<T, Stream<T>> stream(final Arbitrary<T> elementArbitrary) {
        return new VavrStreamArbitrary<>(elementArbitrary);
    }

    public static <T> StreamableArbitrary<T, List<T>> list(final Arbitrary<T> elementArbitrary) {
        return new VavrListArbitrary<>(elementArbitrary);
    }

    public static <T> StreamableArbitrary<T, Queue<T>> queue(final Arbitrary<T> elementArbitrary) {
        return new VavrQueueArbitrary<>(elementArbitrary);
    }

    public static <T extends Comparable<T>> StreamableArbitrary<T, PriorityQueue<T>> priorityQueue(
            final Arbitrary<T> elementArbitrary) {
        return new VavrPriorityQueueArbitrary<>(elementArbitrary);
    }

    public static <T> StreamableArbitrary<T, PriorityQueue<T>> priorityQueue(
            final Arbitrary<T> elementArbitrary,
            final Comparator<T> comparator) {
        return new VavrPriorityQueueArbitrary<>(elementArbitrary, comparator);
    }

    public static <T> StreamableArbitrary<T, Array<T>> array(final Arbitrary<T> elementArbitrary) {
        return new VavrArrayArbitrary<>(elementArbitrary);
    }

    public static <T> StreamableArbitrary<T, Vector<T>> vector(final Arbitrary<T> elementArbitrary) {
        return new VavrVectorArbitrary<>(elementArbitrary);
    }

    public static StreamableArbitrary<Character, CharSeq> charSeq(final Arbitrary<Character> elementArbitrary) {
        return new VavrCharSeqArbitrary(elementArbitrary);
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

//    public static <T extends Comparable<T>> StreamableArbitrary<T, BitSet<T>> bitSet(
//            final Arbitrary<T> elementArbitrary) {
//        return new VavrBitSetArbitrary<>(elementArbitrary);
//    }

    public static <T> StreamableArbitrary<T, Tree<T>> tree(final Arbitrary<T> elementArbitrary) {
        return new VavrTreeArbitrary<>(elementArbitrary);
    }

    public static <K, V> SizableArbitrary<HashMap<K, V>> hashMap(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary) {
        return new VavrHashMapArbitrary<>(keysArbitrary, valuesArbitrary);
    }

    public static <K, V> SizableArbitrary<LinkedHashMap<K, V>> linkedHashMap(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary) {
        return new VavrLinkedHashMapArbitrary<>(keysArbitrary, valuesArbitrary);
    }

    public static <K, V> SizableArbitrary<TreeMap<K, V>> treeMap(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary) {
        return new VavrTreeMapArbitrary<>(keysArbitrary, valuesArbitrary);
    }

    public static <K, V> SizableArbitrary<TreeMap<K, V>> treeMap(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary,
            final Comparator<K> keyComparator) {
        return new VavrTreeMapArbitrary<>(keysArbitrary, valuesArbitrary, keyComparator);
    }

    public static <K, V> SizableArbitrary<HashMultimap<K, V>> hashMultimap(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary) {
        return new VavrHashMultimapArbitrary<>(keysArbitrary, valuesArbitrary);
    }

    public static <K, V> SizableArbitrary<LinkedHashMultimap<K, V>> linkedHashMultimap(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary) {
        return new VavrLinkedHashMultimapArbitrary<>(keysArbitrary, valuesArbitrary);
    }

    public static <K, V> SizableArbitrary<TreeMultimap<K, V>> treeMultimap(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary) {
        return new VavrTreeMultimapArbitrary<>(keysArbitrary, valuesArbitrary);
    }

    public static <K, V> SizableArbitrary<TreeMultimap<K, V>> treeMultimap(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary,
            final Comparator<K> keyComparator) {
        return new VavrTreeMultimapArbitrary<>(keysArbitrary, valuesArbitrary, keyComparator);
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

    public static <K, V> SizableArbitrary<? extends Map<K, V>> map(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary) {
        return hashMap(keysArbitrary, valuesArbitrary);
    }

    public static <K, V> SizableArbitrary<? extends SortedMap<K, V>> sortedMap(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary) {
        return treeMap(keysArbitrary, valuesArbitrary);
    }

    public static <K, V> SizableArbitrary<? extends Multimap<K, V>> multimap(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary) {
        return hashMultimap(keysArbitrary, valuesArbitrary);
    }

    public static <K, V> SizableArbitrary<? extends SortedMultimap<K, V>> sortedMultimap(
            final Arbitrary<K> keysArbitrary,
            final Arbitrary<V> valuesArbitrary) {
        return treeMultimap(keysArbitrary, valuesArbitrary);
    }

}
