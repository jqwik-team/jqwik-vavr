[![Build Status](https://travis-ci.com/Befrish/jqwik-vavr.svg?branch=main)](https://travis-ci.com/Befrish/jqwik-vavr)
[![Build Status](https://github.com/Befrish/jqwik-vavr/workflows/build/badge.svg)](https://github.com/Befrish/jqwik-vavr/actions?query=workflow%3Abuild)
[![Maven metadata URL](https://img.shields.io/maven-metadata/v?color=blue&metadataUrl=https%3A%2F%2Frepo.repsy.io%2Fmvn%2FBefrish%2Fjqwik-vavr%2Fde%2Fbefrish%2Fjqwik%2Fjqwik-vavr)](https://repo.repsy.io/mvn/befrish/jqwik-vavr)

# Vavr support for jqwik

Adds [jqwik](https://jqwik.net/) arbitraries for [Vavr](https://www.vavr.io/vavr-docs/) data structures:

- io.vavr.collection.List
- io.vavr.collection.Set

<!--
- io.vavr.collection.*
- io.vavr.concurrent.*
- io.vavr.control.*
-->

_More collections and data structures will be implemented in later versions. In Version 1.0.0 should all data structures._

## Usage

ToDo examples

```java
@Property
void generateDistinctLists(@ForAll final List<@Unique Integer> list) {
    assertThat(list.distinct().size(), is(list.size()));
}
```

```java
@Provide
Arbitrary<List<Integer>> integersMin3() {
    return VavrArbitraries.list(Arbitraries.integers()).ofMinSize(3);
}

@Property
void generateSizableListFrom(@ForAll @From("integersMin3") final List<Integer> list) {
    assertThat(list.size(), is(greaterThanOrEqualTo(3)));
}
```

## Installation

see [GitHub Packages](https://github.com/users/Befrish/packages?repo_name=jqwik-vavr)
