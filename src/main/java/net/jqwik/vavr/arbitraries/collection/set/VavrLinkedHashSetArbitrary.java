package net.jqwik.vavr.arbitraries.collection.set;

import net.jqwik.vavr.arbitraries.base.SetBasedArbitrary;
import io.vavr.collection.LinkedHashSet;
import net.jqwik.api.Arbitrary;

public class VavrLinkedHashSetArbitrary<T> extends SetBasedArbitrary<T, LinkedHashSet<T>> {

	public VavrLinkedHashSetArbitrary(final Arbitrary<T> elementArbitrary) {
		super(elementArbitrary);
	}

	@Override
	protected LinkedHashSet<T> convertJavaSetToVavrCollection(final java.util.Set<T> javaSet) {
		return LinkedHashSet.ofAll(javaSet);
	}

}
