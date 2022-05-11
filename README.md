[![Build Status](https://github.com/Befrish/jqwik-vavr/workflows/build/badge.svg)](https://github.com/Befrish/jqwik-vavr/actions?query=workflow%3Abuild)
[![Maven metadata URL](https://img.shields.io/maven-metadata/v?color=blue&metadataUrl=https%3A%2F%2Frepo.repsy.io%2Fmvn%2FBefrish%2Fjqwik-vavr%2Fde%2Fbefrish%2Fjqwik%2Fjqwik-vavr%2Fmaven-metadata.xml)](https://repo.repsy.io/mvn/befrish/jqwik-vavr)

# Vavr support for jqwik

Adds [jqwik](https://jqwik.net/) arbitraries for [Vavr](https://www.vavr.io/vavr-docs/) data structures:

- `io.vavr.collection.Iterator`
- `io.vavr.collection.Stream`
- `io.vavr.collection.Seq`
- `io.vavr.collection.LinearSeq`
- `io.vavr.collection.List`
- `io.vavr.collection.Queue`
- `io.vavr.collection.PriorityQueue`
- `io.vavr.collection.IndexedSeq`
- `io.vavr.collection.Array`
- `io.vavr.collection.Vector`
- `io.vavr.collection.CharSeq`
- `io.vavr.collection.Set`
- `io.vavr.collection.HashSet`
- `io.vavr.collection.LinkedHashSet`
- `io.vavr.collection.SortedSet`
- `io.vavr.collection.TreeSet`
- `io.vavr.collection.BitSet` (in the future)
- `io.vavr.collection.Tree`
- `io.vavr.collection.Map`
- `io.vavr.collection.HashMap`
- `io.vavr.collection.LinkedHashMap`
- `io.vavr.collection.SortedMap`
- `io.vavr.collection.TreeMap`
- `io.vavr.collection.Multimap`
- `io.vavr.collection.HashMultimap`
- `io.vavr.collection.LinkedHashMultimap`
- `io.vavr.collection.SortedMultimap`
- `io.vavr.collection.TreeMultimap`

- `io.vavr.Lazy`

- `io.vavr.control.Option`
- `io.vavr.control.Either`
- `io.vavr.control.Try`
- `io.vavr.control.Validation`

- `io.vavr.concurrent.Future`

## Examples

```java
@Property
void generateDistinctLists(@ForAll final io.vavr.collection.List<@Unique Integer> list) {
    assertThat(list.distinct().size(), is(list.size()));
}
```

```java
@Provide
Arbitrary<io.vavr.collection.List<Integer>> integersMin3() {
    return VavrArbitraries.list(Arbitraries.integers()).ofMinSize(3);
}

@Property
void generateSizableListFrom(@ForAll @From("integersMin3") final io.vavr.collection.List<Integer> list) {
    assertThat(list.size(), is(greaterThanOrEqualTo(3)));
}
```

## Installation with Maven

```xml
<repositories>
  <repository>
    <id>jqwik-vavr</id>
    <name>jqwik Vavr Packages</name>
    <url>https://repo.repsy.io/mvn/befrish/jqwik-vavr</url>
  </repository>
</repositories>
```

```xml
<dependency>
  <groupId>de.befrish.jqwik</groupId>
  <artifactId>jqwik-vavr</artifactId>
  <version>LATEST</version>
</dependency>
```
