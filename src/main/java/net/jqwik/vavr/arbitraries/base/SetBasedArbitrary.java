package net.jqwik.vavr.arbitraries.base;

import io.vavr.collection.Set;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.arbitraries.ArbitraryDecorator;
import net.jqwik.api.arbitraries.SetArbitrary;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.RandomDistribution;

import java.util.function.Function;
import java.util.function.BiFunction;

public abstract class SetBasedArbitrary<T, U extends Set<T>> extends ArbitraryDecorator<U>
	implements StreamableArbitrary<T,U> {

	private SetArbitrary<T> setArbitrary;

	public SetBasedArbitrary(final Arbitrary<T> elementArbitrary) {
		this.setArbitrary = elementArbitrary.set();
	}

	protected abstract U convertJavaSetToVavrCollection(java.util.Set<T> javaSet);

	@Override
	protected Arbitrary<U> arbitrary() {
		return setArbitrary.map(this::convertJavaSetToVavrCollection);
	}

	@Override
	public SetBasedArbitrary<T, U> ofMinSize(final int minSize) {
		final SetBasedArbitrary<T, U> clone = typedClone();
		clone.setArbitrary = setArbitrary.ofMinSize(minSize);
		return clone;
	}

	@Override
	public SetBasedArbitrary<T, U> ofMaxSize(final int maxSize) {
		final SetBasedArbitrary<T, U> clone = typedClone();
		clone.setArbitrary = setArbitrary.ofMaxSize(maxSize);
		return clone;
	}

	@Override
	public SetBasedArbitrary<T, U> withSizeDistribution(final RandomDistribution distribution) {
		final SetBasedArbitrary<T, U> clone = typedClone();
		clone.setArbitrary = setArbitrary.withSizeDistribution(distribution);
		return clone;
	}

	@Override
	public <R> Arbitrary<R> reduce(final R initial, final BiFunction<R, T, R> accumulator) {
		return setArbitrary.reduce(initial, accumulator);
	}

	public SetBasedArbitrary<T, U> uniqueElements(final Function<T, Object> by) {
		final SetBasedArbitrary<T, U> clone = typedClone();
		clone.setArbitrary = setArbitrary.uniqueElements(by);
		return clone;
	}

	@Override
	public SetBasedArbitrary<T, U> uniqueElements() {
		// Set based arbitraries have unique elements by default
		return this;
	}
}
