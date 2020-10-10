package de.befrish.jqwik.vavr.arbitraries;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractListBasedVavrArbitrary;
import io.vavr.collection.List;
import io.vavr.collection.Queue;
import net.jqwik.api.Arbitrary;

public class VavrQueueArbitrary<T> extends AbstractListBasedVavrArbitrary<T, Queue<T>> {

	public VavrQueueArbitrary(final Arbitrary<T> elementArbitrary, final boolean elementsUnique) {
		super(elementArbitrary, elementsUnique);
	}

	@Override
	protected Queue<T> convertJavaListToVavrCollection(final java.util.List<T> javaList) {
		return Queue.ofAll(javaList);
	}

}
