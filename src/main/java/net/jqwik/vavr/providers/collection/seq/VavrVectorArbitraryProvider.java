package net.jqwik.vavr.providers.collection.seq;

import io.vavr.collection.Vector;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.seq.VavrVectorArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

public class VavrVectorArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Vector.class;
    }

    @Override
    protected VavrVectorArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrVectorArbitrary<>(innerArbitrary);
    }

}
