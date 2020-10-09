package de.befrish.jqwik.vavr.arbitraries.impl;

import io.vavr.Lazy;
import net.jqwik.api.Arbitrary;

/**
 * @author Benno Müller
 */
public interface VavrLazyArbitrary<T> extends Arbitrary<Lazy<T>> {
}
