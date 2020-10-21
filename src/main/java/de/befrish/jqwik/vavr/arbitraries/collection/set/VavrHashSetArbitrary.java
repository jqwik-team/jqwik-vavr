package de.befrish.jqwik.vavr.arbitraries.collection.set;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractSetBasedArbitrary;
import io.vavr.collection.HashSet;
import net.jqwik.api.Arbitrary;

public class VavrHashSetArbitrary<T> extends AbstractSetBasedArbitrary<T, HashSet<T>> {

	public VavrHashSetArbitrary(final Arbitrary<T> elementArbitrary) {
		super(elementArbitrary);
	}

	@Override
	protected HashSet<T> convertJavaSetToVavrCollection(final java.util.Set<T> javaSet) {
		return HashSet.ofAll(javaSet);
	}

}
