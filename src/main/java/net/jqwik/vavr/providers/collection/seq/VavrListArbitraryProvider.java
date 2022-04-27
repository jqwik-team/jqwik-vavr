package net.jqwik.vavr.providers.collection.seq;

import io.vavr.collection.List;
import net.jqwik.api.Arbitrary;
import net.jqwik.vavr.arbitraries.collection.seq.VavrListArbitrary;
import net.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;

public class VavrListArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return List.class;
    }

    @Override
    protected VavrListArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrListArbitrary<>(innerArbitrary);
    }

    @Override
    public int priority() {
        return 2; //  provider for Seq and LinearSeq
    }

}
