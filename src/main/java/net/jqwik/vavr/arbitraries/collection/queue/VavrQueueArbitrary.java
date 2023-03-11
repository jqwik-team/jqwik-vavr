package net.jqwik.vavr.arbitraries.collection.queue;

import net.jqwik.vavr.arbitraries.base.ListBasedArbitrary;
import io.vavr.collection.Queue;
import net.jqwik.api.Arbitrary;

public class VavrQueueArbitrary<T> extends ListBasedArbitrary<T, Queue<T>> {

	public VavrQueueArbitrary(final Arbitrary<T> elementArbitrary) {
		super(elementArbitrary);
	}

	@Override
	protected Queue<T> convertJavaListToVavrCollection(final java.util.List<T> javaList) {
		return Queue.ofAll(javaList);
	}

}
