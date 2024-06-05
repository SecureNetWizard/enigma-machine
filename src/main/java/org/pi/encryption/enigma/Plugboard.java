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
public class Plugboard implements EncryptLetter {
	
	public static final int SWITCH_ENTRIES = 10;
	public static final int SWITCH_POSITIONS = 26;

	private final int[] table = new int[SWITCH_POSITIONS];
	
	public Plugboard(String switchString) {
		String[] c = switchString.toUpperCase().split(" ");
		
		if (c.length != SWITCH_ENTRIES)
			throw new IllegalArgumentException("invalid pairs in the switchString");
		
		for (int i = 0; i < SWITCH_POSITIONS; i ++)
			table[i] = i;
		
		for (String pair: c) {
			if (pair.length() != 2)
				throw new IllegalArgumentException("invalid pairs in the switchString");
			
			int a = pair.charAt(0) - 'A';
			int b = pair.charAt(1) - 'A';
			
			if(a > SWITCH_POSITIONS || b > SWITCH_POSITIONS)
				throw new IllegalArgumentException("pairs are out of bounds in the switchString");
			
			table[a] = b;
			table[b] = a;
		}
	}

	/**
	 * @see org.pi.encryption.enigma.EncryptLetter#applyInt(int)
	 */
	@Override
	public int applyInt(int ch) {
		ch = table[ch];
		
		return ch;
	}

}
