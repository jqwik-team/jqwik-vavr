package net.jqwik.vavr.arbitraries.base;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.EdgeCases;
import net.jqwik.api.RandomDistribution;
import net.jqwik.api.RandomGenerator;
import net.jqwik.api.Shrinkable;
import net.jqwik.api.arbitraries.StreamableArbitrary;
import net.jqwik.api.configurators.ArbitraryConfigurator;
import net.jqwik.api.configurators.SelfConfiguringArbitrary;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.api.providers.TypeUsage;
import net.jqwik.engine.properties.FeatureExtractor;
import net.jqwik.engine.properties.arbitraries.EdgeCasesSupport;
import net.jqwik.engine.properties.arbitraries.TypedCloneable;
import net.jqwik.engine.properties.arbitraries.randomized.RandomGenerators;
import net.jqwik.engine.support.JqwikReflectionSupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static net.jqwik.engine.properties.UniquenessChecker.checkUniquenessOfShrinkables;

abstract class MultivalueArbitrary<T, U> extends TypedCloneable
		implements StreamableArbitrary<T, U>, SelfConfiguringArbitrary<U> {

	protected Arbitrary<T> elementArbitrary;

	protected int minSize = 0;
	protected int maxSize = RandomGenerators.DEFAULT_COLLECTION_SIZE;
	protected Set<FeatureExtractor<T>> uniquenessExtractors = new HashSet<>();
	protected RandomDistribution sizeDistribution = null;

	protected MultivalueArbitrary(final Arbitrary<T> elementArbitrary) {
		this.elementArbitrary = elementArbitrary;
	}

	@Override
	public StreamableArbitrary<T, U> ofMinSize(final int minSize) {
		final MultivalueArbitrary<T, U> clone = typedClone();
		clone.minSize = minSize;
		return clone;
	}

	@Override
	public StreamableArbitrary<T, U> ofMaxSize(final int maxSize) {
		final MultivalueArbitrary<T, U> clone = typedClone();
		clone.maxSize = maxSize;
		return clone;
	}

	@Override
	public StreamableArbitrary<T, U> withSizeDistribution(final RandomDistribution distribution) {
		final MultivalueArbitrary<T, U> clone = typedClone();
		clone.sizeDistribution = distribution;
		return clone;
	}

	@Override
	public <R> Arbitrary<R> reduce(final R initial, final BiFunction<R, T, R> accumulator) {
		return this.map(streamable -> {
			// Couldn't find a way to use Stream.reduce since it requires a combinator
			@SuppressWarnings("unchecked") final R[] result = (R[]) new Object[]{initial};
			final Iterable<T> iterable = toIterable(streamable);
			for (final T each : iterable) {
				result[0] = accumulator.apply(result[0], each);
			}
			return result[0];
		});
	}

	protected abstract Iterable<T> toIterable(U streamable);

	@Override
	public Arbitrary<U> configure(final ArbitraryConfigurator configurator, final TypeUsage targetType) {
		targetType.getComponentType().ifPresent(elementType -> this.elementArbitrary
				= configurator.configure(this.elementArbitrary, elementType));

		// see UniqueElementsConfigurator
		final Arbitrary<U> arbitraryUniqueElements = configureUniqueElements(this, targetType);

		return configurator.configure(arbitraryUniqueElements, targetType);
	}

	@SuppressWarnings("unchecked")
	private Arbitrary<U> configureUniqueElements(
			final MultivalueArbitrary<T, U> arbitrary,
			final TypeUsage targetType) {
		return targetType.findAnnotation(UniqueElements.class)
				.map((UniqueElements uniqueness) -> {
					final Function<T, Object> extractor = (Function<T, Object>) extractor(uniqueness);
					return (Arbitrary<U>) uniqueElements(extractor::apply);
				})
				.orElse(arbitrary);
	}

	private static Function<?, Object> extractor(final UniqueElements uniqueness) {
		final Class<? extends Function<?, Object>> extractorClass = uniqueness.by();
		return extractorClass.equals(UniqueElements.NOT_SET.class)
				? Function.identity()
				// TODO: Create instance in context of test instance.
				//       This requires an extension of ArbitraryConfiguration interface
				//       to provide access to PropertyLifecycleContext
				: JqwikReflectionSupport.newInstanceWithDefaultConstructor(extractorClass);
	}

	protected StreamableArbitrary<T, U> uniqueElements(final FeatureExtractor<T> by) {
		final MultivalueArbitrary<T, U> clone = typedClone();
		clone.uniquenessExtractors = new HashSet<>(this.uniquenessExtractors);
		clone.uniquenessExtractors.add(by);
		return clone;
	}

	protected RandomGenerator<List<T>> createListGenerator(final int genSize, final boolean withEmbeddedEdgeCases) {
		final RandomGenerator<T> elementGenerator = elementGenerator(this.elementArbitrary, genSize, withEmbeddedEdgeCases);
		return RandomGenerators.list(
				elementGenerator,
				this.minSize,
				this.maxSize,
				genSize,
				this.sizeDistribution,
				this.uniquenessExtractors);
	}

	protected RandomGenerator<T> elementGenerator(
			final Arbitrary<T> elementArbitrary,
			final int genSize,
			final boolean withEdgeCases) {
		return elementArbitrary.generator(genSize, withEdgeCases);
	}

	protected <C extends Collection<?>> EdgeCases<C> edgeCases(
			final BiFunction<List<Shrinkable<T>>, Integer, Shrinkable<C>> shrinkableCreator,
			final int maxEdgeCases
	) {
		final EdgeCases<C> emptyListEdgeCase = (this.minSize == 0) ? emptyListEdgeCase(shrinkableCreator) : EdgeCases.none();
		final EdgeCases<C> singleElementEdgeCases = (this.minSize <= 1 && this.maxSize >= 1)
				? fixedSizeEdgeCases(1, shrinkableCreator) : EdgeCases.none();
		final EdgeCases<C> fixedSizeEdgeCases = generateFixedSizeEdgeCases()
				? fixedSizeEdgeCases(this.minSize, shrinkableCreator)
				: EdgeCases.none();
		return EdgeCasesSupport.concat(asList(
				emptyListEdgeCase,
				singleElementEdgeCases,
				fixedSizeEdgeCases
		), maxEdgeCases);
	}

	private boolean generateFixedSizeEdgeCases() {
		return this.minSize == this.maxSize && this.minSize > 1;
	}

	private <C extends Collection<?>> EdgeCases<C> fixedSizeEdgeCases(
			final int fixedSize,
			final BiFunction<List<Shrinkable<T>>, Integer, Shrinkable<C>> shrinkableCreator
	) {
		return EdgeCasesSupport.mapShrinkable(
				this.elementArbitrary.edgeCases(),
				shrinkableT -> {
					final List<Shrinkable<T>> elements = new ArrayList<>(Collections.nCopies(fixedSize, shrinkableT));
					if (!checkUniquenessOfShrinkables(this.uniquenessExtractors, elements)) {
						return null;
					}
					return shrinkableCreator.apply(elements, this.minSize);
				}
		);
	}

	private <C extends Collection<?>> EdgeCases<C> emptyListEdgeCase(
			final BiFunction<List<Shrinkable<T>>, Integer, Shrinkable<C>> shrinkableCreator) {
		return EdgeCases.fromSupplier(
				() -> shrinkableCreator.apply(Collections.emptyList(), this.minSize)
		);
	}

}
