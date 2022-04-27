package net.jqwik.vavr.arbitraries.collection.seq;

import net.jqwik.vavr.arbitraries.base.ListBasedVavrArbitrary;
import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;

public class VavrListArbitrary<T> extends ListBasedVavrArbitrary<T, List<T>> {

	public VavrListArbitrary(final Arbitrary<T> elementArbitrary) {
		super(elementArbitrary);
	}

	@Override
	protected List<T> convertJavaListToVavrCollection(final java.util.List<T> javaList) {
		return List.ofAll(javaList);
	}

}
