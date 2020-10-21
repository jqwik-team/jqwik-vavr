package de.befrish.jqwik.vavr.arbitraries;

import de.befrish.jqwik.vavr.arbitraries.base.SinglevalueArbitraryBase;
import io.vavr.Lazy;
import io.vavr.control.Option;
import net.jqwik.api.Arbitrary;

public class VavrOptionArbitrary<T> extends SinglevalueArbitraryBase<T, Option<T>> implements Arbitrary<Option<T>> {

    public VavrOptionArbitrary(final Arbitrary<T> innerArbitrary) {
        super(innerArbitrary);
    }

    @Override
    protected Option<T> mapValue(final T value) {
        return Option.of(value);
    }

}
