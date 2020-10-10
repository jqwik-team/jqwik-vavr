package de.befrish.jqwik.vavr.arbitraries;

import de.befrish.jqwik.vavr.NaturalComparator;
import de.befrish.jqwik.vavr.arbitraries.base.AbstractSetBasedArbitrary;
import io.vavr.collection.TreeSet;
import net.jqwik.api.Arbitrary;

import java.util.Comparator;

public class VavrTreeSetArbitrary<T> extends AbstractSetBasedArbitrary<T, TreeSet<T>> {

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
