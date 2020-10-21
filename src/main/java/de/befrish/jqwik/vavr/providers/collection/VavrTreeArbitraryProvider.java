package de.befrish.jqwik.vavr.providers.collection;

import de.befrish.jqwik.vavr.arbitraries.collection.VavrTreeArbitrary;
import de.befrish.jqwik.vavr.providers.base.AbstractSingleTypeArbitraryProvider;
import io.vavr.collection.Tree;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import org.kohsuke.MetaInfServices;

@MetaInfServices(ArbitraryProvider.class)
public class VavrTreeArbitraryProvider extends AbstractSingleTypeArbitraryProvider {

    @Override
    protected Class<?> getProvidedType() {
        return Tree.class;
    }

    @Override
    protected VavrTreeArbitrary<?> create(final Arbitrary<?> innerArbitrary) {
        return new VavrTreeArbitrary<>(innerArbitrary, innerArbitrary.isUnique());
    }

}
