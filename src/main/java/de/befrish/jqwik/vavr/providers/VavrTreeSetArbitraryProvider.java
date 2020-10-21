package de.befrish.jqwik.vavr.providers;

import de.befrish.jqwik.vavr.arbitraries.VavrTreeSetArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;
import io.vavr.collection.TreeSet;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrTreeSetArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return TreeSet.class;
    }

    @Override
    protected VavrTreeSetArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrTreeSetArbitrary<>(innerArbitrary);
    }

    @Override
    public int priority() {
        return 1; //  provider for SortedSet
    }

}
