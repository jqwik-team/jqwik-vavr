package de.befrish.jqwik.vavr.arbitraries;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractSetBasedArbitrary;
import io.vavr.collection.LinkedHashSet;
import net.jqwik.api.Arbitrary;

public class VavrLinkedHashSetArbitrary<T> extends AbstractSetBasedArbitrary<T, LinkedHashSet<T>> {

	public VavrLinkedHashSetArbitrary(Arbitrary<T> elementArbitrary) {
		super(elementArbitrary);
	}

	@Override
	protected LinkedHashSet<T> convertJavaSetToVavrCollection(java.util.Set<T> javaSet) {
		return LinkedHashSet.ofAll(javaSet);
	}

}
