/*
 * Sly Technologies Free License
 * 
 * Copyright 2023 Sly Technologies Inc.
 *
 * Licensed under the Sly Technologies Free License (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.slytechs.com/free-license-text
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.pi.encryption.enigma;

/**
 * @author Sly Technologies Inc
 * @author repos@slytechs.com
 *
 */
public class Plugboard {

	public static int[][] parsePlugboardPairs(String plugBoardPairs) {
		String[] c = plugBoardPairs.toUpperCase().split("\\s+");
		int[][] pairs = new int[c.length][];

		if (c.length > SWITCH_MAX_ENTRIES)
			throw new IllegalArgumentException("invalid pairs in the plugBoardPairs");

		for (int i = 0; i < c.length; i++) {
			String pair = c[i];
			if (pair.length() != 2)
				throw new IllegalArgumentException("invalid pairs in the plugBoardPairs");

			int a = pair.charAt(0) - 'A';
			int b = pair.charAt(1) - 'A';

			if (a > SWITCH_POSITIONS || b > SWITCH_POSITIONS)
				throw new IllegalArgumentException("pairs are out of bounds in the plugBoardPairs");

			pairs[i] = new int[] {a, b};
		}

		return pairs;
	}

	public static final int SWITCH_MAX_ENTRIES = 10;
	public static final int SWITCH_POSITIONS = 26;

	private final int[] table = new int[SWITCH_POSITIONS];

	public Plugboard() {
		for (int i = 0; i < SWITCH_POSITIONS; i++)
			table[i] = i;
	}

	public int exchange(int ch) {
		ch = table[ch];

		return ch;
	}

	public void setPlugboard(String plugBoardPairs) {
		int[][] pairs = parsePlugboardPairs(plugBoardPairs);

		for (int[] pair : pairs) {
			table[pair[0]] = pair[1];
		}
	}
}
