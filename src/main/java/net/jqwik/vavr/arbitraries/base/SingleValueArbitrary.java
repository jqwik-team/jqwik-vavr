package net.jqwik.vavr.arbitraries.base;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.Tuple;
import net.jqwik.api.arbitraries.ArbitraryDecorator;
import net.jqwik.api.configurators.ArbitraryConfigurator;
import net.jqwik.api.configurators.SelfConfiguringArbitrary;
import net.jqwik.api.providers.TypeUsage;

import java.util.ArrayList;
import java.util.List;

public abstract class SingleValueArbitrary<T, U> extends ArbitraryDecorator<U> implements Arbitrary<U>, SelfConfiguringArbitrary<U> {

    private final Arbitrary<T> innerArbitrary;
    private final List<Tuple.Tuple2<ArbitraryConfigurator, TypeUsage>> configurations = new ArrayList<>();
    private Arbitrary<T> configuredInnerArbitrary;

    public SingleValueArbitrary(final Arbitrary<T> innerArbitrary) {
        this.innerArbitrary = innerArbitrary;
    }

    protected abstract U mapValue(T value);

    @Override
    protected Arbitrary<U> arbitrary() {
        return getConfiguredInnerArbitrary().map(this::mapValue);
    }

    @Override
    public Arbitrary<U> configure(final ArbitraryConfigurator configurator, final TypeUsage targetType) {
        this.configurations.add(Tuple.of(configurator, targetType));
        return this;
    }

    private Arbitrary<T> getConfiguredInnerArbitrary() {
        if (this.configuredInnerArbitrary == null) {
            this.configuredInnerArbitrary = this.innerArbitrary;
            for (final Tuple.Tuple2<ArbitraryConfigurator, TypeUsage> configuration : this.configurations) {
                final ArbitraryConfigurator configurator = configuration.get1();
                final TypeUsage targetType = configuration.get2();
                this.configuredInnerArbitrary = SelfConfiguringArbitrary
                        .configure(this.configuredInnerArbitrary, configurator, targetType);
            }
        }
        return this.configuredInnerArbitrary;
    }

}
