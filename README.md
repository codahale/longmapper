# LongMapper

[![Build Status](https://secure.travis-ci.org/codahale/longmapper.svg)](http://travis-ci.org/codahale/longmapper)

## Add to your project

```xml
<dependency>
  <groupId>com.codahale</groupId>
  <artifactId>longmapper</artifactId>
  <version>0.1.0</version>
</dependency>
```

## Use the thing

```java
import com.codahale.longmapper.LongMapper;

class Example {
  public static void main(String... args) {
    final LongMapper mapper = new LongMapper("ayellowsubmarine".getBytes("UTF-8"));

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
Benchmarks.map    avgt   20  114.266 ± 3.772  ns/op
Benchmarks.unmap  avgt   20  109.040 ± 2.356  ns/op

```

## License

Copyright © 2018 Coda Hale

Distributed under the Apache License 2.0.
