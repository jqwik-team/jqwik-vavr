package de.befrish.jqwik.vavr;

import net.jqwik.api.Shrinkable;
import net.jqwik.api.ShrinkingDistance;
import net.jqwik.engine.properties.shrinking.AbstractValueShrinkable;

import java.util.stream.Stream;

/**
 * @author Benno Müller
 */
public class SingletonShrinkable<T> extends AbstractValueShrinkable<T> {

    private SingletonShrinkable(final T value) {
        super(value);
    }

    public static <T> SingletonShrinkable<T> of(final T value) {
        return new SingletonShrinkable<>(value);
    }

    @Override
    public Stream<Shrinkable<T>> shrink() {
        return Stream.of(new SingletonShrinkable(value()));
    }

    @Override
    public ShrinkingDistance distance() {
        return ShrinkingDistance.of(Long.MIN_VALUE);
    }

}
