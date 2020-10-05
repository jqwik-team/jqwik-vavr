[![Build Status](https://travis-ci.com/Befrish/jqwik-vavr.svg?branch=main)](https://travis-ci.com/Befrish/jqwik-vavr)

# Vavr support for jqwik

Adds [jqwik](https://jqwik.net/) arbitraries for [Vavr](https://www.vavr.io/vavr-docs/) data structures:

- io.vavr.collection.List
- io.vavr.collection.Set

<!--
- io.vavr.collection.*
- io.vavr.concurrent.*
- io.vavr.control.*
-->

---
**NOTE**
More collections and data structures will be implemented in later versions. In Version 1.0.0 should all data structures. 
---

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

### Maven

---
**WARNING**
The repository is not available now until there is one released version.
---

```xml
<repositories>
  <repository>
    <id>github-jqwik-vavr</id>
    <name>jqwik Vavr Packages</name>
    <url>https://maven.pkg.github.com/Befrish/jqwik-vavr</url>
  </repository>
</repositories>
```

```xml
<dependency>
    <groupId>de.befrish.jqwik</groupId>
    <artifactId>jqwik-vavr</artifactId>
    <version>0.0.1</version>
</dependency>
```
