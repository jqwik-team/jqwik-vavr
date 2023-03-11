package net.jqwik.vavr.arbitraries.collection.seq;

import net.jqwik.vavr.arbitraries.base.ListBasedArbitrary;
import io.vavr.collection.Array;
import net.jqwik.api.Arbitrary;

public class VavrArrayArbitrary<T> extends ListBasedArbitrary<T, Array<T>> {

	public VavrArrayArbitrary(final Arbitrary<T> elementArbitrary) {
		super(elementArbitrary);
	}

	@Override
	protected Array<T> convertJavaListToVavrCollection(final java.util.List<T> javaList) {
		return Array.ofAll(javaList);
	}

}
