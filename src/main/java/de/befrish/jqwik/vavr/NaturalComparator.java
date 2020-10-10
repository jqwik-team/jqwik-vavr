package de.befrish.jqwik.vavr;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @see io.vavr.collection.NaturalComparator
 *
 * @author Benno Müller
 */
public class NaturalComparator<T> implements Comparator<T>, Serializable {

    private static final long serialVersionUID = 1L;

    private static final NaturalComparator<?> INSTANCE = new NaturalComparator<>();

    private NaturalComparator() {
    }

    @SuppressWarnings("unchecked")
    public static <T> NaturalComparator<T> instance() {
        return (NaturalComparator<T>) INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int compare(final T o1, final T o2) {
        return ((Comparable<T>) o1).compareTo(o2);
    }

    /**
     * @see Comparator#equals(Object)
     */
    @Override
    public boolean equals(final Object obj) {
        return obj instanceof NaturalComparator;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Instance control for object serialization.
     *
     * @return The singleton instance of NaturalComparator.
     * @see java.io.Serializable
     */
    private Object readResolve() {
        return INSTANCE;
    }

}
