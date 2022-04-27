package net.jqwik.vavr.providers.collection.seq;

import io.vavr.collection.CharSeq;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;
import net.jqwik.vavr.arbitraries.collection.seq.VavrCharSeqArbitrary;

import java.util.Collections;
import java.util.Set;

public class VavrCharSeqArbitraryProvider implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isAssignableFrom(CharSeq.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        return Collections.singleton(new VavrCharSeqArbitrary(Arbitraries.chars()));
    }

    @Override
    public int priority() {
        return -1; //  match last (very specialised)
    }

}
