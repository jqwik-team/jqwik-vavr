package de.befrish.jqwik.vavr.providers.collection.seq;

import de.befrish.jqwik.vavr.arbitraries.collection.seq.VavrCharSeqArbitrary;
import io.vavr.collection.CharSeq;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;
import org.kohsuke.MetaInfServices;

import java.util.Collections;
import java.util.Set;

@MetaInfServices(ArbitraryProvider.class)
public class VavrCharSeqArbitraryProvider implements ArbitraryProvider {

    @Override
    public boolean canProvideFor(final TypeUsage targetType) {
        return targetType.isAssignableFrom(CharSeq.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
        return Collections.singleton(new VavrCharSeqArbitrary(Arbitraries.chars(), false));
    }

    @Override
    public int priority() {
        return -1; //  match last (very specialised)
    }

}
