package de.befrish.jqwik.vavr.arbitraries.collection.set;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractSetBasedArbitrary;
import io.vavr.collection.LinkedHashSet;
import net.jqwik.api.Arbitrary;

public class VavrLinkedHashSetArbitrary<T> extends AbstractSetBasedArbitrary<T, LinkedHashSet<T>> {

	public VavrLinkedHashSetArbitrary(final Arbitrary<T> elementArbitrary) {
		super(elementArbitrary);
	}

	@Override
	protected LinkedHashSet<T> convertJavaSetToVavrCollection(final java.util.Set<T> javaSet) {
		return LinkedHashSet.ofAll(javaSet);
	}

}
