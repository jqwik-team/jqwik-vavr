package net.jqwik.vavr.arbitraries.collection.seq;

import net.jqwik.vavr.arbitraries.base.ListBasedArbitrary;
import io.vavr.collection.CharSeq;
import net.jqwik.api.Arbitrary;

public class VavrCharSeqArbitrary extends ListBasedArbitrary<Character, CharSeq> {

	public VavrCharSeqArbitrary(final Arbitrary<Character> elementArbitrary) {
		super(elementArbitrary);
	}

	@Override
	protected CharSeq convertJavaListToVavrCollection(final java.util.List<Character> javaList) {
		return CharSeq.ofAll(javaList);
	}

}
