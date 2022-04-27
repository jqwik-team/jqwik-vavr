package net.jqwik.vavr.arbitraries.base;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Traversable;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.RandomDistribution;
import net.jqwik.api.arbitraries.ArbitraryDecorator;
import net.jqwik.engine.properties.arbitraries.DefaultMapArbitrary;
import net.jqwik.vavr.api.arbitraries.MapArbitrary;
import net.jqwik.engine.properties.FeatureExtractor;
import net.jqwik.engine.properties.arbitraries.randomized.RandomGenerators;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * @see net.jqwik.engine.properties.arbitraries.DefaultMapArbitrary
 *
 * @param <K> Key type
 * @param <V> Value type
 * @param <U> Map type
 */
public abstract class MapBasedArbitrary<K, V, U extends Traversable<Tuple2<K, V>>>
		extends ArbitraryDecorator<U>
		implements MapArbitrary<K, V, U> {

	private final Arbitrary<K> keysArbitrary;
	private final Arbitrary<V> valuesArbitrary;

	private int minSize = 0;
	private int maxSize = RandomGenerators.DEFAULT_COLLECTION_SIZE;
//	private RandomDistribution sizeDistribution = null;

	private Set<FeatureExtractor<K>> keyUniquenessExtractors = new HashSet<>();
	private Set<FeatureExtractor<V>> valueUniquenessExtractors = new HashSet<>();

	public MapBasedArbitrary(final Arbitrary<K> keysArbitrary, final Arbitrary<V> valuesArbitrary) {
		this.keysArbitrary = keysArbitrary;
		this.valuesArbitrary = valuesArbitrary;
	}

	@Override
	public MapArbitrary<K, V, U> ofMinSize(final int minSize) {
		final MapBasedArbitrary<K, V, U> clone = typedClone();
		clone.minSize = minSize;
		return clone;
	}

	@Override
	public MapArbitrary<K, V, U> ofMaxSize(final int maxSize) {
		final MapBasedArbitrary<K, V, U> clone = typedClone();
		clone.maxSize = maxSize;
		return clone;
	}

	@Override
	public MapArbitrary<K, V, U> withSizeDistribution(final RandomDistribution distribution) {
		final MapBasedArbitrary<K, V, U> clone = typedClone();
//		clone.sizeDistribution = distribution; // TODO
		return clone;
	}

	@Override
	protected Arbitrary<U> arbitrary() {
		// Using list of generated Map.Entry does not work because of potential duplicate keys
		final Arbitrary<List<K>> keySets = this.keysArbitrary.set().ofMinSize(this.minSize).ofMaxSize(this.maxSize)
				.map(List::ofAll);
		return keySets.flatMap(keys -> this.valuesArbitrary.list().ofSize(keys.size())
				.map(values -> convertTupleListToVavrMap(keys.zip(values))));
	}

	protected abstract U convertTupleListToVavrMap(List<Tuple2<K, V>> tuple2List);

	@Override
	public MapArbitrary<K, V, U> uniqueKeys(final Function<K, Object> by) {
		final MapBasedArbitrary<K, V, U> clone = typedClone();
		clone.keyUniquenessExtractors = new HashSet<>(this.keyUniquenessExtractors);
		clone.keyUniquenessExtractors.add(by::apply);
		return clone;
	}

	@Override
	public MapArbitrary<K, V, U> uniqueValues(final Function<V, Object> by) {
		final MapBasedArbitrary<K, V, U> clone = typedClone();
		clone.valueUniquenessExtractors = new HashSet<>(this.valueUniquenessExtractors);
		clone.valueUniquenessExtractors.add(by::apply);
		return clone;
	}

	@Override
	public MapArbitrary<K, V, U> uniqueValues() {
		return uniqueValues(FeatureExtractor.identity());
	}

}
