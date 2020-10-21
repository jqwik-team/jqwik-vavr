package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.VavrVectorArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;
import io.vavr.collection.Vector;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrVectorArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Vector.class;
    }

    @Override
    protected VavrVectorArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrVectorArbitrary<>(innerArbitrary, innerArbitrary.isUnique());
    }

}
