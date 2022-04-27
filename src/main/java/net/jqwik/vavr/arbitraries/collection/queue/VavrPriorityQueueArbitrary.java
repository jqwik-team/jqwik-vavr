package net.jqwik.vavr.arbitraries.collection.queue;

import net.jqwik.vavr.NaturalComparator;
import net.jqwik.vavr.arbitraries.base.ListBasedVavrArbitrary;
import io.vavr.collection.PriorityQueue;
import net.jqwik.api.Arbitrary;

import java.util.Comparator;

public class VavrPriorityQueueArbitrary<T> extends ListBasedVavrArbitrary<T, PriorityQueue<T>> {

	private final Comparator<T> comparator;

	public VavrPriorityQueueArbitrary(
			final Arbitrary<T> elementArbitrary,
			final Comparator<T> comparator) {
		super(elementArbitrary);
		this.comparator = comparator;
	}

	public VavrPriorityQueueArbitrary(final Arbitrary<T> elementArbitrary) {
		this(elementArbitrary, NaturalComparator.instance());
	}

	@Override
	protected PriorityQueue<T> convertJavaListToVavrCollection(final java.util.List<T> javaList) {
		return PriorityQueue.ofAll(this.comparator, javaList);
	}

}
