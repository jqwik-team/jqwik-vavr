package net.jqwik.vavr.arbitraries.base;

import io.vavr.collection.Traversable;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.arbitraries.ArbitraryDecorator;
import net.jqwik.api.arbitraries.ListArbitrary;
import net.jqwik.api.RandomDistribution;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class ListBasedVavrArbitrary<T, U extends Traversable<T>> extends ArbitraryDecorator<U>
		implements StreamableArbitrary<T,U> {

	private ListArbitrary<T> listArbitrary;

	public ListBasedVavrArbitrary(final Arbitrary<T> elementArbitrary) {
		this.listArbitrary = elementArbitrary.list();
	}

	protected abstract U convertJavaListToVavrCollection(java.util.List<T> javaList);

	@Override
	protected Arbitrary<U> arbitrary() {
		return listArbitrary.map(this::convertJavaListToVavrCollection);
	}

	@Override
	public ListBasedVavrArbitrary<T, U> ofMinSize(final int minSize) {
		final ListBasedVavrArbitrary<T, U> clone = typedClone();
		clone.listArbitrary = listArbitrary.ofMinSize(minSize);
		return clone;
	}

	@Override
	public ListBasedVavrArbitrary<T, U> ofMaxSize(final int maxSize) {
		final ListBasedVavrArbitrary<T, U> clone = typedClone();
		clone.listArbitrary = listArbitrary.ofMaxSize(maxSize);
		return clone;
	}

	@Override
	public ListBasedVavrArbitrary<T, U> withSizeDistribution(final RandomDistribution distribution) {
		final ListBasedVavrArbitrary<T, U> clone = typedClone();
		clone.listArbitrary = listArbitrary.withSizeDistribution(distribution);
		return clone;
	}

	@Override
	public <R> Arbitrary<R> reduce(final R initial, final BiFunction<R, T, R> accumulator) {
		return listArbitrary.reduce(initial, accumulator);
	}

	public ListBasedVavrArbitrary<T, U> uniqueElements(final Function<T, Object> by) {
		final ListBasedVavrArbitrary<T, U> clone = typedClone();
		clone.listArbitrary = listArbitrary.uniqueElements(by);
		return clone;
	}

}
