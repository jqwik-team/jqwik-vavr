package net.jqwik.vavr.arbitraries.collection.set;

import net.jqwik.vavr.NaturalComparator;
import net.jqwik.vavr.arbitraries.base.SetBasedArbitrary;
import io.vavr.collection.TreeSet;
import net.jqwik.api.Arbitrary;

import java.util.Comparator;

public class VavrTreeSetArbitrary<T> extends SetBasedArbitrary<T, TreeSet<T>> {

	private final Comparator<T> comparator;

	public VavrTreeSetArbitrary(final Arbitrary<T> elementArbitrary, final Comparator<T> comparator) {
		super(elementArbitrary);
		this.comparator = comparator;
	}

	public VavrTreeSetArbitrary(final Arbitrary<T> elementArbitrary) {
		this(elementArbitrary, NaturalComparator.instance());
	}

	@Override
	protected TreeSet<T> convertJavaSetToVavrCollection(final java.util.Set<T> javaSet) {
		return TreeSet.ofAll(this.comparator, javaSet);
	}

}
