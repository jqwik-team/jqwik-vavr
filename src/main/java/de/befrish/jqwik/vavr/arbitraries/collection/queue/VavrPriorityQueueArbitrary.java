package de.befrish.jqwik.vavr.arbitraries.collection.queue;

import de.befrish.jqwik.vavr.NaturalComparator;
import de.befrish.jqwik.vavr.arbitraries.base.AbstractListBasedVavrArbitrary;
import io.vavr.collection.PriorityQueue;
import net.jqwik.api.Arbitrary;

import java.util.Comparator;

public class VavrPriorityQueueArbitrary<T> extends AbstractListBasedVavrArbitrary<T, PriorityQueue<T>> {

	private final Comparator<T> comparator;

	public VavrPriorityQueueArbitrary(
			final Arbitrary<T> elementArbitrary,
			final boolean elementsUnique,
			final Comparator<T> comparator) {
		super(elementArbitrary, elementsUnique);
		this.comparator = comparator;
	}

	public VavrPriorityQueueArbitrary(final Arbitrary<T> elementArbitrary, final boolean elementsUnique) {
		this(elementArbitrary, elementsUnique, NaturalComparator.instance());
	}

	@Override
	protected PriorityQueue<T> convertJavaListToVavrCollection(final java.util.List<T> javaList) {
		return PriorityQueue.ofAll(this.comparator, javaList);
	}

}
