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

It encrypts each long as a 64-bit [XTEA](https://en.wikipedia.org/wiki/XTEA) block. This doesn't do
much from a cryptographic perspective, but if your universe consists only of 64-bit integers, it's
sufficient for obfuscation.

## Performance

It's fast. Plenty fast.

```
Benchmark         Mode  Cnt    Score   Error  Units
Benchmarks.map    avgt   20  115.175 ± 1.564  ns/op
Benchmarks.unmap  avgt   20  119.450 ± 1.194  ns/op
```

## License

Copyright © 2018 Coda Hale

Distributed under the Apache License 2.0.
