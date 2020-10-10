package de.befrish.jqwik.vavr.arbitraries;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractListBasedVavrArbitrary;
import io.vavr.collection.Array;
import net.jqwik.api.Arbitrary;

public class VavrArrayArbitrary<T> extends AbstractListBasedVavrArbitrary<T, Array<T>> {

	public VavrArrayArbitrary(final Arbitrary<T> elementArbitrary, final boolean elementsUnique) {
		super(elementArbitrary, elementsUnique);
	}

	@Override
	protected Array<T> convertJavaListToVavrCollection(java.util.List<T> javaList) {
		return Array.ofAll(javaList);
	}

}
