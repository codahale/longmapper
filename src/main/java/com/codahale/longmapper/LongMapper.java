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

package com.codahale.longmapper;

/** A class which uniquely maps 64-bit integers to other 64-bit integers. */
public class LongMapper {
  private static final int ROUNDS = 32;
  private static final int DELTA = 0x9E3779B9;
  private final int[] k;

  /**
   * Creates a new mapper with the given key.
   *
   * @param key a 16-byte key, unique to the mapping
   */
  public LongMapper(byte[] key) {
    if (key.length != 16) {
      throw new IllegalArgumentException("Key must be 16 bytes long");
    }
    this.k =
        new int[] {
          key[0] << 24 | (key[1] & 0xFF) << 16 | (key[2] & 0xFF) << 8 | (key[3] & 0xFF),
          key[4] << 24 | (key[5] & 0xFF) << 16 | (key[6] & 0xFF) << 8 | (key[7] & 0xFF),
          key[8] << 24 | (key[9] & 0xFF) << 16 | (key[10] & 0xFF) << 8 | (key[11] & 0xFF),
          key[12] << 24 | (key[13] & 0xFF) << 16 | (key[14] & 0xFF) << 8 | (key[15] & 0xFF)
        };
  }

  /**
   * Maps a {@code long} to another {@code long}.
   *
   * @param input an arbitrary {@code long}
   * @return another {@code long}, uniquely mapped to by {@code input}
   */
  public long map(long input) {
    int v0 = (int) (input >> 32), v1 = (int) input, sum = 0;
    for (int i = 0; i < ROUNDS; i++) {
      v0 += (((v1 << 4) ^ (v1 >> 5)) + v1) ^ (sum + k[sum & 3]);
      sum += DELTA;
      v1 += (((v0 << 4) ^ (v0 >> 5)) + v0) ^ (sum + k[(sum >> 11) & 3]);
    }
    return (long) v0 << 32 | (v1 & 0xFFFFFFFFL);
  }

  /**
   * Un-maps the result of {@link #map(long)}.
   *
   * @param input a mapped {@code long}
   * @return the original {@code long}
   */
  public long unmap(long input) {
    int v0 = (int) (input >> 32), v1 = (int) input, sum = (int) ((long) DELTA * ROUNDS);
    for (int i = 0; i < ROUNDS; i++) {
      v1 -= (((v0 << 4) ^ (v0 >> 5)) + v0) ^ (sum + k[(sum >> 11) & 3]);
      sum -= DELTA;
      v0 -= (((v1 << 4) ^ (v1 >> 5)) + v1) ^ (sum + k[sum & 3]);
    }
    return (long) v0 << 32 | (v1 & 0xFFFFFFFFL);
  }
}
