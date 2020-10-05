package de.befrish.jqwik.vavr.arbitraries;

import io.vavr.collection.Set;
import net.jqwik.api.arbitraries.StreamableArbitrary;

/**
 * @author Benno Müller
 */
public interface VavrSetArbitrary<T> extends StreamableArbitrary<T, Set<T>> {

    /**
     * Fix the size to {@code size}.
     *
     * @param size The size of the generated list
     * @return new arbitrary instance
     */
    @Override
    default VavrSetArbitrary<T> ofSize(final int size) {
        return ofMinSize(size).ofMaxSize(size);
    }

    /**
     * Set lower size boundary {@code minSize} (included).
     *
     * @param minSize The minimum size of the generated list
     * @return new arbitrary instance
     */
    @Override
    VavrSetArbitrary<T> ofMinSize(int minSize);

    /**
     * Set upper size boundary {@code maxSize} (included).
     *
     * @param maxSize The maximum size of the generated list
     * @return new arbitrary instance
     */
    @Override
    VavrSetArbitrary<T> ofMaxSize(int maxSize);
    
}
