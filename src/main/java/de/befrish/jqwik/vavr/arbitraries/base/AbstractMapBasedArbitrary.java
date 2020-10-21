package de.befrish.jqwik.vavr.arbitraries.base;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Traversable;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.ExhaustiveGenerator;
import net.jqwik.api.RandomGenerator;
import net.jqwik.api.Shrinkable;
import net.jqwik.api.arbitraries.SizableArbitrary;
import net.jqwik.engine.properties.arbitraries.AbstractArbitraryBase;
import net.jqwik.engine.properties.arbitraries.EdgeCasesSupport;
import net.jqwik.engine.properties.arbitraries.randomized.RandomGenerators;

import java.util.Arrays;
import java.util.Optional;

/**
 * @see net.jqwik.engine.properties.arbitraries.DefaultMapArbitrary
 *
 * @param <K> Key type
 * @param <V> Value type
 * @param <U> Map type
 */
public abstract class AbstractMapBasedArbitrary<K, V, U extends Traversable<Tuple2<K, V>>> extends AbstractArbitraryBase
		implements SizableArbitrary<U> {

	private final Arbitrary<K> keysArbitrary;
	private final Arbitrary<V> valuesArbitrary;

	private int minSize = 0;
	private int maxSize = RandomGenerators.DEFAULT_COLLECTION_SIZE;

	public AbstractMapBasedArbitrary(final Arbitrary<K> keysArbitrary, final Arbitrary<V> valuesArbitrary) {
		this.keysArbitrary = keysArbitrary;
		this.valuesArbitrary = valuesArbitrary;
	}

	protected abstract U convertTupleListToVavrMap(List<Tuple2<K, V>> tuple2List);

	@Override
	public SizableArbitrary<U> ofMinSize(final int minSize) {
		final AbstractMapBasedArbitrary<K, V, U> clone = typedClone();
		clone.minSize = minSize;
		return clone;
	}

	@Override
	public SizableArbitrary<U> ofMaxSize(final int maxSize) {
		final AbstractMapBasedArbitrary<K, V, U> clone = typedClone();
		clone.maxSize = maxSize;
		return clone;
	}

	@Override
	public RandomGenerator<U> generator(final int genSize) {
		return mapArbitrary().generator(genSize);
	}

	private Arbitrary<U> mapArbitrary() {
		// Using list of generated Map.Entry does not work because of potential duplicate keys
		final Arbitrary<List<K>> keySets = this.keysArbitrary.set().ofMinSize(this.minSize).ofMaxSize(this.maxSize)
				.map(List::ofAll);
		return keySets.flatMap(keys -> this.valuesArbitrary.list().ofSize(keys.size())
				.map(values -> convertTupleListToVavrMap(keys.zip(values))));
	}

	@Override
	public Optional<ExhaustiveGenerator<U>> exhaustive(final long maxNumberOfSamples) {
		return mapArbitrary().exhaustive(maxNumberOfSamples);
	}

	@Override
	public EdgeCases<U> edgeCases() {
		final EdgeCases<U> emptyMapEdgeCase =
				this.minSize == 0
						? EdgeCases.fromSupplier(() -> Shrinkable.unshrinkable(convertTupleListToVavrMap(List.empty())))
						: EdgeCases.none();
		final EdgeCases<U> singleEntryEdgeCases =
				this.minSize <= 1
						? singleEntryEdgeCases()
						: EdgeCases.none();
		return EdgeCasesSupport.concat(Arrays.asList(emptyMapEdgeCase, singleEntryEdgeCases));
	}

	private EdgeCases<U> singleEntryEdgeCases() {
		return this.keysArbitrary.edgeCases()
				.flatMapArbitrary(key -> this.valuesArbitrary
						.map(value -> convertTupleListToVavrMap(List.of(Tuple.of(key, value)))));
	}

}
