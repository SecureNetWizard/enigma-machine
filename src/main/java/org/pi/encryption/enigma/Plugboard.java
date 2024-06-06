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

/**
 * Class representing the plugboard component of the Enigma machine.
 * The plugboard allows for swapping pairs of letters for an extra layer of encryption.
 * 
 * This implementation includes methods for parsing plugboard pair strings, setting the plugboard,
 * and exchanging characters according to the plugboard configuration.
 * 
 * @version 1.0
 * @since 2023
 */
public class Plugboard {

    /**
     * The maximum number of plugboard entries allowed.
     */
    public static final int SWITCH_MAX_ENTRIES = 10;

    /**
     * The number of switch positions (letters in the alphabet).
     */
    public static final int SWITCH_POSITIONS = 26;

    /**
     * Parses a string of plugboard pairs and converts them into an array of integer pairs.
     * 
     * @param plugBoardPairs the string of plugboard pairs (e.g., "AB CD EF")
     * @return an array of integer pairs representing the plugboard connections
     * @throws IllegalArgumentException if the input string is invalid
     */
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

    /**
     * The internal table representing plugboard connections.
     */
    private final int[] table = new int[SWITCH_POSITIONS];

    /**
     * Constructs a new plugboard with default settings (no connections).
     */
    public Plugboard() {
        for (int i = 0; i < SWITCH_POSITIONS; i++)
            table[i] = i;
    }

    /**
     * Exchanges the input character according to the plugboard configuration.
     * 
     * @param ch the input character (as an integer)
     * @return the exchanged character (as an integer)
     */
    public int exchange(int ch) {
        ch = table[ch];
        return ch;
    }

    /**
     * Sets the plugboard configuration using a string of letter pairs.
     * 
     * @param plugBoardPairs the string of plugboard pairs (e.g., "AB CD EF")
     */
    public void setPlugboard(String plugBoardPairs) {
        int[][] pairs = parsePlugboardPairs(plugBoardPairs);

        for (int[] pair : pairs) {
            table[pair[0]] = pair[1];
            table[pair[1]] = pair[0];
        }
    }
}
