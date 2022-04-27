package net.jqwik.vavr.api.arbitraries;

import io.vavr.Tuple2;
import io.vavr.collection.Traversable;
import net.jqwik.api.arbitraries.SizableArbitrary;
import org.apiguardian.api.API;

import java.util.Map;
import java.util.function.Function;

import static org.apiguardian.api.API.Status.MAINTAINED;

/**
 * Fluent interface to add functionality to arbitraries that generate instances
 * of type {@linkplain io.vavr.collection.Traversable<io.vavr.Tuple2>} as Map base representation in Vavr.
 *
 * @author Benno Müller
 */
@API(status = MAINTAINED, since = "2.0.0")
public interface MapArbitrary<K, V, U extends Traversable<Tuple2<K, V>>> extends SizableArbitrary<U> {

    /**
     * Fix the size to {@code size}.
     *
     * @param size The size of the generated map
     * @return new arbitrary instance
     */
    @Override
    default MapArbitrary<K, V, U> ofSize(final int size) {
        return ofMinSize(size).ofMaxSize(size);
    }

    /**
     * Set lower size boundary {@code minSize} (included).
     *
     * @param minSize The minimum size of the generated map
     * @return new arbitrary instance
     */
    @Override
    MapArbitrary<K, V, U> ofMinSize(int minSize);

    /**
     * Set upper size boundary {@code maxSize} (included).
     *
     * @param maxSize The maximum size of the generated map
     * @return new arbitrary instance
     */
    @Override
    MapArbitrary<K, V, U> ofMaxSize(int maxSize);

    /**
     * Add the constraint that keys of the generated map must be unique
     * relating to an element's "feature" being extracted by applying the
     * {@code by} function on a map entry's key.
     * The extracted features are being compared using {@linkplain Object#equals(Object)}.
     *
     * <p>
     * The constraint can be combined with other {@linkplain #uniqueKeys(Function)} constraints.
     * </p>
     *
     * @return new arbitrary instance
     */
    @API(status = MAINTAINED, since = "2.0.0")
    MapArbitrary<K, V, U> uniqueKeys(Function<K, Object> by);

    /**
     * Add the constraint that value of the generated map must be unique
     * relating to an element's "feature" being extracted by applying the
     * {@code by} function on a map entry's value.
     * The extracted features are being compared using {@linkplain Object#equals(Object)}.
     *
     * <p>
     * The constraint can be combined with other {@linkplain #uniqueValues(Function)} constraints.
     * </p>
     *
     * @return new arbitrary instance
     */
    @API(status = MAINTAINED, since = "2.0.0")
    MapArbitrary<K, V, U> uniqueValues(Function<V, Object> by);

    /**
     * Add the constraint that values of the generated map must be unique,
     * i.e. no two value must return true when being compared using {@linkplain Object#equals(Object)}.
     *
     * <p>
     * The constraint can be combined with other {@linkplain #uniqueValues(Function)} constraints.
     * </p>
     *
     * @return new arbitrary instance
     */
    @API(status = MAINTAINED, since = "2.0.0")
    MapArbitrary<K, V, U> uniqueValues();

}
