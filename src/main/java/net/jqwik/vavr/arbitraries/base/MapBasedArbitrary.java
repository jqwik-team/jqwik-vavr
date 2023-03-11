package net.jqwik.vavr.arbitraries.base;

import io.vavr.Tuple2;
import io.vavr.collection.Traversable;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.RandomDistribution;
import net.jqwik.api.arbitraries.ArbitraryDecorator;
import net.jqwik.vavr.api.arbitraries.MapArbitrary;

import java.util.function.Function;

/**
 * @param <K> Key type
 * @param <V> Value type
 * @param <U> Map type
 */
public abstract class MapBasedArbitrary<K, V, U extends Traversable<Tuple2<K, V>>>
		extends ArbitraryDecorator<U>
		implements MapArbitrary<K, V, U> {

	private net.jqwik.api.arbitraries.MapArbitrary<K, V> javaMapArbitrary;

	public MapBasedArbitrary(final Arbitrary<K> keysArbitrary, final Arbitrary<V> valuesArbitrary) {
		this.javaMapArbitrary = Arbitraries.maps(keysArbitrary, valuesArbitrary);
	}

	@Override
	public MapArbitrary<K, V, U> ofMinSize(final int minSize) {
		final MapBasedArbitrary<K, V, U> clone = typedClone();
		clone.javaMapArbitrary = javaMapArbitrary.ofMinSize(minSize);
		return clone;
	}

	@Override
	public MapArbitrary<K, V, U> ofMaxSize(final int maxSize) {
		final MapBasedArbitrary<K, V, U> clone = typedClone();
		clone.javaMapArbitrary = javaMapArbitrary.ofMaxSize(maxSize);
		return clone;
	}

	@Override
	public MapArbitrary<K, V, U> withSizeDistribution(final RandomDistribution distribution) {
		final MapBasedArbitrary<K, V, U> clone = typedClone();
		clone.javaMapArbitrary = javaMapArbitrary.withSizeDistribution(distribution);
		return clone;
	}

	@Override
	protected Arbitrary<U> arbitrary() {
		return javaMapArbitrary.map(this::convertJavaMapToVavrMap);
	}

	protected abstract U convertJavaMapToVavrMap(java.util.Map<K, V> javaMap);

	@Override
	public MapArbitrary<K, V, U> uniqueKeys(final Function<K, Object> by) {
		final MapBasedArbitrary<K, V, U> clone = typedClone();
		clone.javaMapArbitrary = javaMapArbitrary.uniqueKeys(by);
		return clone;
	}

	@Override
	public MapArbitrary<K, V, U> uniqueValues(final Function<V, Object> by) {
		final MapBasedArbitrary<K, V, U> clone = typedClone();
		clone.javaMapArbitrary = javaMapArbitrary.uniqueValues(by);
		return clone;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MapArbitrary<K, V, U> uniqueValues() {
		return uniqueValues((Function<V, Object>) Function.identity());
	}

}
