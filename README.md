# LongMapper

[![CircleCI](https://circleci.com/gh/codahale/longmapper.svg?style=svg)](https://circleci.com/gh/codahale/longmapper)

## Add to your project

```xml
<dependency>
  <groupId>com.codahale</groupId>
  <artifactId>longmapper</artifactId>
  <version>0.1.1</version>
</dependency>
```

*Note: module name for Java 9+ is `com.codahale.longmapper`.*

## Use the thing

```java
import static java.nio.charset.StandardCharsets.UTF_8;
import com.codahale.longmapper.LongMapper;

class Example {
  public static void main(String... args) {
    final LongMapper mapper = new LongMapper("ayellowsubmarine".getBytes(UTF_8));

    System.out.printf("Mapped %d to %d\n", 200, mapper.map(200));
    System.out.printf("Unmapped %d to %d\n", mapper.map(200), mapper.unmap(mapper.map(200)));
  }
}
```

## How it works

It encrypts each `long` as a 64-bit [Blowfish](https://en.wikipedia.org/wiki/Blowfish_(cipher)) 
block.

## Performance

It's fast. Plenty fast.

```
Benchmark         Mode  Cnt    Score   Error  Units
Benchmarks.map    avgt   20  102.132 ± 8.190  ns/op
Benchmarks.unmap  avgt   20  115.311 ± 1.092  ns/op
```

## License

Copyright © 2018 Coda Hale

Distributed under the Apache License 2.0.
