package de.befrish.jqwik.vavr.arbitraries.collection.seq;

import de.befrish.jqwik.vavr.arbitraries.base.AbstractListBasedVavrArbitrary;
import io.vavr.collection.CharSeq;
import net.jqwik.api.Arbitrary;

public class VavrCharSeqArbitrary extends AbstractListBasedVavrArbitrary<Character, CharSeq> {

	public VavrCharSeqArbitrary(final Arbitrary<Character> elementArbitrary, final boolean elementsUnique) {
		super(elementArbitrary, elementsUnique);
	}

	@Override
	protected CharSeq convertJavaListToVavrCollection(final java.util.List<Character> javaList) {
		return CharSeq.ofAll(javaList);
	}

}
