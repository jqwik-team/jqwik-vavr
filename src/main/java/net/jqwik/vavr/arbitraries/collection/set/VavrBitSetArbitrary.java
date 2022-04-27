package net.jqwik.vavr.arbitraries.collection.set;

import net.jqwik.vavr.arbitraries.base.SetBasedArbitrary;
import io.vavr.collection.BitSet;
import net.jqwik.api.Arbitrary;

import java.util.Set;

/**
 * @author Benno Müller
 */
public class VavrBitSetArbitrary<T> extends SetBasedArbitrary<T, BitSet<T>> {

    public VavrBitSetArbitrary(final Arbitrary<T> elementArbitrary) {
        super(elementArbitrary);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected BitSet convertJavaSetToVavrCollection(final java.util.Set<T> javaSet) {
        if (javaSet.isEmpty()) {
            return BitSet.empty();
        } else {
            final Class<?> elementClass = javaSet.iterator().next().getClass();
            if (Integer.class.isAssignableFrom(elementClass)) {
                final Set<Integer> javaSet1 = (Set<Integer>) javaSet;
                return BitSet.ofAll(javaSet1);
            } else if (Short.class.isAssignableFrom(elementClass)) {
                final Set<Short> javaSet1 = (Set<Short>) javaSet;
                return BitSet.withShorts().ofAll(javaSet1);
            } else if (Long.class.isAssignableFrom(elementClass)) {
                final Set<Long> javaSet1 = (Set<Long>) javaSet;
                return BitSet.withLongs().ofAll(javaSet1);
            } else if (Boolean.class.isAssignableFrom(elementClass)) {
                final Set<Boolean> javaSet1 = (Set<Boolean>) javaSet;
                // see io.vavr.collection.BitSet.ofAll(boolean...)
                return BitSet.withRelations(i -> i != 0, b -> b ? 1 : 0).ofAll(javaSet1);
            } else if (Byte.class.isAssignableFrom(elementClass)) {
                final Set<Byte> javaSet1 = (Set<Byte>) javaSet;
                return BitSet.withBytes().ofAll(javaSet1);
            } else if (Character.class.isAssignableFrom(elementClass)) {
                final Set<Character> javaSet1 = (Set<Character>) javaSet;
                return BitSet.withCharacters().ofAll(javaSet1);
            } else if (Enum.class.isAssignableFrom(elementClass)) {
                final Set<Enum> javaSet1 = (Set<Enum>) javaSet;
                return BitSet.withEnum((Class<Enum>) elementClass).ofAll(javaSet1);
            } else {
                throw new IllegalArgumentException("element class [" + elementClass.getName() + "] not supported for "
                        + BitSet.class.getName());
            }
        }
    }

}
