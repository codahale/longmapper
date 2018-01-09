/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codahale.longmapper.tests;

import com.codahale.longmapper.LongMapper;
import java.util.Arrays;
import org.junit.Test;
import org.quicktheories.WithQuickTheories;
import org.quicktheories.core.Gen;
import org.quicktheories.impl.Constraint;

public class LongMapperTest implements WithQuickTheories {
  @Test
  public void mappingIsBijective() {
    qt().forAll(keys(), longs().all())
        .check(
            (k, in) -> {
              final LongMapper mapper = new LongMapper(k);
              return mapper.unmap(mapper.map(in)) == in;
            });
  }

  @Test
  public void mappingIsKeyDependent() {
    qt().forAll(keys(), keys(), longs().all())
        .assuming((k1, k2, in) -> !Arrays.equals(k1, k2))
        .check(
            (k1, k2, in) -> {
              final LongMapper a = new LongMapper(k1);
              final LongMapper b = new LongMapper(k2);
              return b.unmap(a.map(in)) != in;
            });
  }

  private static Gen<byte[]> keys() {
    final Gen<byte[]> gen =
        prng -> {
          final byte[] bytes = new byte[(int) prng.next(Constraint.between(16, 16))];
          for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) prng.next(Constraint.between(0, 255));
          }
          return bytes;
        };
    return gen.describedAs(Arrays::toString);
  }
}
