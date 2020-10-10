package de.befrish.jqwik.vavr.arbitraries;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractListBasedVavrArbitrary;
import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;

public class VavrListArbitrary<T> extends AbstractListBasedVavrArbitrary<T, List<T>> {

	public VavrListArbitrary(final Arbitrary<T> elementArbitrary, final boolean elementsUnique) {
		super(elementArbitrary, elementsUnique);
	}

	@Override
	protected List<T> convertJavaListToVavrCollection(java.util.List<T> javaList) {
		return List.ofAll(javaList);
	}

}
