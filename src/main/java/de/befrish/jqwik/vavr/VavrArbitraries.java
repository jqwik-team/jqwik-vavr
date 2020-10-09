package de.befrish.jqwik.vavr;

import de.befrish.jqwik.vavr.arbitraries.VavrListArbitrary;
import de.befrish.jqwik.vavr.arbitraries.VavrSetArbitrary;
import de.befrish.jqwik.vavr.arbitraries.impl.DefaultVavrLazyArbitrary;
import de.befrish.jqwik.vavr.arbitraries.impl.DefaultVavrListArbitrary;
import de.befrish.jqwik.vavr.arbitraries.impl.DefaultVavrSetArbitrary;
import de.befrish.jqwik.vavr.arbitraries.impl.VavrLazyArbitrary;
import net.jqwik.api.Arbitrary;

/**
 * @author Benno Müller
 */
public final class VavrArbitraries {

    private VavrArbitraries() {
        super();
    }

    public static <T> VavrLazyArbitrary<T> lazy(final Arbitrary<T> innerArbitrary) {
        return new DefaultVavrLazyArbitrary<>(innerArbitrary);
    }

    public static <T> VavrListArbitrary<T> list(final Arbitrary<T> elementArbitrary) {
        return new DefaultVavrListArbitrary<>(elementArbitrary, elementArbitrary.isUnique());
    }

    public static <T> VavrSetArbitrary<T> set(final Arbitrary<T> elementArbitrary) {
        return new DefaultVavrSetArbitrary<>(elementArbitrary);
    }

}
