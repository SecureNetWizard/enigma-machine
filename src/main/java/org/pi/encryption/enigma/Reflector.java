/*
 * MIT License
 * 
 * Copyright 2023 Sly Technologies Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
 * The Reflector class represents the reflector component of the Enigma machine.
 * It is responsible for reflecting the signal back through the rotors.
 * 
 * This implementation includes settings for the B and C reflectors.
 * 
 * @version 1.0
 * @since 2023
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Enigma_machine">Enigma machine - Wikipedia</a>
 * @see <a href="https://www.codesandciphers.org.uk/enigma">Enigma Cipher Machine - Codes and Ciphers</a>
 * 
 * <p>
 * This class uses an index-based approach to select the appropriate reflector settings.
 * </p>
 * 
 * <pre>
 * Example usage:
 * 
 * Reflector reflector = new Reflector(Reflector.REFLECTOR_B);
 * int reflectedChar = reflector.reflect(inputChar);
 * </pre>
 * 
 * <p>
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
 * <p>
 * Example usage:
 * 
 * <pre>
 * Reflector reflector = new Reflector(Reflector.REFLECTOR_B);
 * int reflectedChar = reflector.reflect(inputChar);
 * </pre>
 * </p>
 * 
 * @version 1.0
 * @since 2023
 * @see <a href="https://en.wikipedia.org/wiki/Enigma_machine">Enigma machine - Wikipedia</a>
 * @see <a href="https://www.codesandciphers.org.uk/enigma">Enigma Cipher Machine - Codes and Ciphers</a>
 * </pre>
 * 
 * 
 * </pre>
 * 
 * @version 1.0
 * @since 2023
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Enigma_machine">Enigma machine - Wikipedia</a>
 * @see <a href="https://www.codesandciphers.org.uk/enigma">Enigma Cipher Machine - Codes and Ciphers</a>
 * 
 * @author Sly Technologies Inc
 * @author repos@slytechs.com
 */
public class Reflector {

    /**
     * Parses the input reflector string and returns the corresponding reflector index.
     * 
     * @param reflectorString the input reflector string ("B" or "C")
     * @return the corresponding reflector index
     * @throws IllegalArgumentException if the input string is invalid
     */
    public static int parseReflectorString(String reflectorString) {
        switch (reflectorString) {
            case "B":
                return 0;
            case "C":
                return 1;
            default:
                throw new IllegalArgumentException("invalid reflector string");
        }
    }

    /**
     * Reflector index for Reflector B.
     */
    public static final int REFLECTOR_B = 0;

    /**
     * Reflector index for Reflector C.
     */
    public static final int REFLECTOR_C = 1;

    /**
     * Array of reflector settings for each reflector type.
     */
    public static final int[][] REFLECTORS = {
        {
            // Reflector B settings
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
        },
        {
            // Reflector C settings
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
        },
    };

    private final int[] settings;

    /**
     * Constructs a new Reflector with the specified reflector index.
     * 
     * @param index the 1-based index of the reflector (1 for Reflector B, 2 for Reflector C)
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Reflector(int index) {
        Objects.checkIndex(index, REFLECTORS.length);
        settings = REFLECTORS[index];
    }

    /**
     * Reflects the input character according to the reflector settings.
     * 
     * @param ch the input character (as an integer)
     * @return the reflected character (as an integer)
     */
    public int reflect(int ch) {
        ch = settings[ch];
        return ch;
    }
}
