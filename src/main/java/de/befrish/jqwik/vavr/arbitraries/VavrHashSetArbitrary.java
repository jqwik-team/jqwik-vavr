package de.befrish.jqwik.vavr.arbitraries;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractSetBasedArbitrary;
import io.vavr.collection.HashSet;
import net.jqwik.api.Arbitrary;

public class VavrHashSetArbitrary<T> extends AbstractSetBasedArbitrary<T, HashSet<T>> {

	public VavrHashSetArbitrary(Arbitrary<T> elementArbitrary) {
		super(elementArbitrary);
	}

	@Override
	protected HashSet<T> convertJavaSetToVavrCollection(java.util.Set<T> javaSet) {
		return HashSet.ofAll(javaSet);
	}

}
