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

import java.util.Objects;

/**
 * <pre>
 * Reflector Settings Table:
 * 
 * Reflector B:
 * | Reflector | Input Letter | Output Letter |
 * |-----------|--------------|---------------|
 * | B         | A            | Y             |
 * | B         | B            | R             |
 * | B         | C            | U             |
 * | B         | D            | H             |
 * | B         | E            | Q             |
 * | B         | F            | S             |
 * | B         | G            | L             |
 * | B         | H            | D             |
 * | B         | I            | P             |
 * | B         | J            | X             |
 * | B         | K            | N             |
 * | B         | L            | G             |
 * | B         | M            | O             |
 * | B         | N            | K             |
 * | B         | O            | M             |
 * | B         | P            | I             |
 * | B         | Q            | E             |
 * | B         | R            | B             |
 * | B         | S            | F             |
 * | B         | T            | Z             |
 * | B         | U            | C             |
 * | B         | V            | W             |
 * | B         | W            | V             |
 * | B         | X            | J             |
 * | B         | Y            | A             |
 * | B         | Z            | T             |
 *
 * Reflector C:
 * | Reflector | Input Letter | Output Letter |
 * |-----------|--------------|---------------|
 * | C         | A            | F             |
 * | C         | B            | V             |
 * | C         | C            | P             |
 * | C         | D            | J             |
 * | C         | E            | I             |
 * | C         | F            | A             |
 * | C         | G            | O             |
 * | C         | H            | Y             |
 * | C         | I            | E             |
 * | C         | J            | D             |
 * | C         | K            | R             |
 * | C         | L            | Z             |
 * | C         | M            | X             |
 * | C         | N            | W             |
 * | C         | O            | G             |
 * | C         | P            | C             |
 * | C         | Q            | T             |
 * | C         | R            | K             |
 * | C         | S            | U             |
 * | C         | T            | Q             |
 * | C         | U            | S             |
 * | C         | V            | B             |
 * | C         | W            | N             |
 * | C         | X            | M             |
 * | C         | Y            | H             |
 * | C         | Z            | L             |
 * </pre>
 * 
 * @author Sly Technologies Inc
 * @author repos@slytechs.com
 *
 */
public class Reflector implements EncryptLetter {

	public static final int REFLECTOR_B = 0;
	public static final int REFLECTOR_C = 1;

	public static final int[][] REFLECTORS = {
			{
			// @formatter:off
    	        24, // A -> Y
    	        17, // B -> R
    	        20, // C -> U
    	        7,  // D -> H
    	        16, // E -> Q
    	        18, // F -> S
    	        11, // G -> L
    	        3,  // H -> D
    	        15, // I -> P
    	        23, // J -> X
    	        13, // K -> N
    	        6,  // L -> G
    	        14, // M -> O
    	        10, // N -> K
    	        12, // O -> M
    	        8,  // P -> I
    	        4,  // Q -> E
    	        1,  // R -> B
    	        5,  // S -> F
    	        25, // T -> Z
    	        2,  // U -> C
    	        22, // V -> W
    	        21, // W -> V
    	        9,  // X -> J
    	        0,  // Y -> A
    	        19  // Z -> T
    		// @formatter:on
			},

			{
			// @formatter:off
                5,  // A -> F
                21, // B -> V
                15, // C -> P
                9,  // D -> J
                8,  // E -> I
                0,  // F -> A
                14, // G -> O
                24, // H -> Y
                4,  // I -> E
                3,  // J -> D
                17, // K -> R
                25, // L -> Z
                23, // M -> X
                22, // N -> W
                6,  // O -> G
                2,  // P -> C
                19, // Q -> T
                10, // R -> K
                20, // S -> U
                16, // T -> Q
                18, // U -> S
                1,  // V -> B
                13, // W -> N
                12, // X -> M
                7,  // Y -> H
                11  // Z -> L
            // @formatter:on
			},
	};

	private final int[] settings;

	/**
	 * A 1-based rotator index from 1 to 5, as per original German design.
	 *
	 * @param index the 1-based index
	 */
	public Reflector(int index) {
		Objects.checkIndex(index, REFLECTORS.length);
		settings = REFLECTORS[index];
	}

	/**
	 * @see org.pi.encryption.enigma.EncryptLetter#applyInt(int)
	 */
	@Override
	public int applyInt(int ch) {
		ch = settings[ch];

		return ch;
	}
}
