package net.jqwik.vavr.arbitraries.collection.set;

import net.jqwik.vavr.arbitraries.base.SetBasedArbitrary;
import io.vavr.collection.HashSet;
import net.jqwik.api.Arbitrary;

public class VavrHashSetArbitrary<T> extends SetBasedArbitrary<T, HashSet<T>> {

	public VavrHashSetArbitrary(final Arbitrary<T> elementArbitrary) {
		super(elementArbitrary);
	}

	@Override
	protected HashSet<T> convertJavaSetToVavrCollection(final java.util.Set<T> javaSet) {
		return HashSet.ofAll(javaSet);
	}

}
