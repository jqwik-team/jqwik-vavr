package de.befrish.jqwik.vavr.arbitraries.collection.seq;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractListBasedVavrArbitrary;
import io.vavr.collection.Array;
import io.vavr.collection.Vector;
import net.jqwik.api.Arbitrary;

public class VavrVectorArbitrary<T> extends AbstractListBasedVavrArbitrary<T, Vector<T>> {

	public VavrVectorArbitrary(final Arbitrary<T> elementArbitrary, final boolean elementsUnique) {
		super(elementArbitrary, elementsUnique);
	}

	@Override
	protected Vector<T> convertJavaListToVavrCollection(final java.util.List<T> javaList) {
		return Vector.ofAll(javaList);
	}

}
