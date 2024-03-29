package net.jqwik.vavr.arbitraries.collection.seq;

import net.jqwik.vavr.arbitraries.base.ListBasedArbitrary;
import io.vavr.collection.Vector;
import net.jqwik.api.Arbitrary;

public class VavrVectorArbitrary<T> extends ListBasedArbitrary<T, Vector<T>> {

	public VavrVectorArbitrary(final Arbitrary<T> elementArbitrary) {
		super(elementArbitrary);
	}

	@Override
	protected Vector<T> convertJavaListToVavrCollection(final java.util.List<T> javaList) {
		return Vector.ofAll(javaList);
	}

}
