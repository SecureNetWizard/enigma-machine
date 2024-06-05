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

import java.util.Arrays;
import java.util.Objects;

/**
 * <pre>
 * Rotor Settings Table:
 * 
 * | Rotor Number | Input Letter | Output Letter |
 * |--------------|--------------|---------------|
 * | I            | A            | E             |
 * | I            | B            | K             |
 * | I            | C            | M             |
 * | I            | D            | F             |
 * | I            | E            | L             |
 * | I            | F            | G             |
 * | I            | G            | D             |
 * | I            | H            | Q             |
 * | I            | I            | V             |
 * | I            | J            | Z             |
 * | I            | K            | N             |
 * | I            | L            | T             |
 * | I            | M            | O             |
 * | I            | N            | W             |
 * | I            | O            | Y             |
 * | I            | P            | H             |
 * | I            | Q            | X             |
 * | I            | R            | U             |
 * | I            | S            | S             |
 * | I            | T            | P             |
 * | I            | U            | A             |
 * | I            | V            | I             |
 * | I            | W            | B             |
 * | I            | X            | R             |
 * | I            | Y            | C             |
 * | I            | Z            | J             |
 * | II           | A            | A             |
 * | II           | B            | J             |
 * | II           | C            | D             |
 * | II           | D            | K             |
 * | II           | E            | S             |
 * | II           | F            | I             |
 * | II           | G            | R             |
 * | II           | H            | U             |
 * | II           | I            | X             |
 * | II           | J            | B             |
 * | II           | K            | L             |
 * | II           | L            | H             |
 * | II           | M            | W             |
 * | II           | N            | T             |
 * | II           | O            | M             |
 * | II           | P            | C             |
 * | II           | Q            | Q             |
 * | II           | R            | G             |
 * | II           | S            | Z             |
 * | II           | T            | N             |
 * | II           | U            | P             |
 * | II           | V            | Y             |
 * | II           | W            | F             |
 * | II           | X            | V             |
 * | II           | Y            | O             |
 * | II           | Z            | E             |
 * | III          | A            | B             |
 * | III          | B            | D             |
 * | III          | C            | F             |
 * | III          | D            | H             |
 * | III          | E            | J             |
 * | III          | F            | L             |
 * | III          | G            | C             |
 * | III          | H            | P             |
 * | III          | I            | R             |
 * | III          | J            | T             |
 * | III          | K            | X             |
 * | III          | L            | V             |
 * | III          | M            | Z             |
 * | III          | N            | N             |
 * | III          | O            | Y             |
 * | III          | P            | E             |
 * | III          | Q            | I             |
 * | III          | R            | W             |
 * | III          | S            | G             |
 * | III          | T            | A             |
 * | III          | U            | K             |
 * | III          | V            | M             |
 * | III          | W            | U             |
 * | III          | X            | S             |
 * | III          | Y            | Q             |
 * | III          | Z            | O             |
 * | IV           | A            | E             |
 * | IV           | B            | S             |
 * | IV           | C            | O             |
 * | IV           | D            | V             |
 * | IV           | E            | P             |
 * | IV           | F            | Z             |
 * | IV           | G            | J             |
 * | IV           | H            | A             |
 * | IV           | I            | Y             |
 * | IV           | J            | Q             |
 * | IV           | K            | U             |
 * | IV           | L            | I             |
 * | IV           | M            | R             |
 * | IV           | N            | H             |
 * | IV           | O            | X             |
 * | IV           | P            | L             |
 * | IV           | Q            | N             |
 * | IV           | R            | F             |
 * | IV           | S            | T             |
 * | IV           | T            | G             |
 * | IV           | U            | K             |
 * | IV           | V            | D             |
 * | IV           | W            | C             |
 * | IV           | X            | M             |
 * | IV           | Y            | W             |
 * | IV           | Z            | B             |
 * | V            | A            | V             |
 * | V            | B            | Z             |
 * | V            | C            | B             |
 * | V            | D            | R             |
 * | V            | E            | G             |
 * | V            | F            | I             |
 * | V            | G            | T             |
 * | V            | H            | Y             |
 * | V            | I            | U             |
 * | V            | J            | P             |
 * | V            | K            | S             |
 * | V            | L            | D             |
 * | V            | M            | N             |
 * | V            | N            | H             |
 * | V            | O            | L             |
 * | V            | P            | X             |
 * | V            | Q            | A             |
 * | V            | R            | W             |
 * | V            | S            | M             |
 * | V            | T            | J             |
 * | V            | U            | Q             |
 * | V            | V            | O             |
 * | V            | W            | F             |
 * | V            | X            | E             |
 * | V            | Y            | C             |
 * | V            | Z            | K             |
 * |--------------|--------------|---------------|
 * </pre>
 * 
 * The Rotor class represents a rotor component of the Enigma machine.
 * It is responsible for the substitution of characters as they pass through the Enigma machine.
 * 
 * This implementation includes settings for rotors I through V.
 * 
 * @version 1.0
 * @since 2023
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Enigma_machine">Enigma machine - Wikipedia</a>
 * @see <a href="https://www.codesandciphers.org.uk/enigma">Enigma Cipher Machine - Codes and Ciphers</a>
 * 
 * <p>
 * Example usage:
 * 
 * <pre>
 * Rotor rotor = new Rotor(Rotor.ROTOR_I);
 * rotor.setDial(0);
 * int encryptedChar = rotor.forward(inputChar);
 * int decryptedChar = rotor.reverse(encryptedChar);
 * </pre>
 * </p>
 * 
 * <p>
 * Rotor Settings Table:
 * 
 * | Rotor Number | Input Letter | Output Letter |
 * |--------------|--------------|---------------|
 * | I            | A            | E             |
 * | I            | B            | K             |
 * | I            | C            | M             |
 * | I            | D            | F             |
 * | I            | E            | L             |
 * | I            | F            | G             |
 * | I            | G            | D             |
 * | I            | H            | Q             |
 * | I            | I            | V             |
 * | I            | J            | Z             |
 * | I            | K            | N             |
 * | I            | L            | T             |
 * | I            | M            | O             |
 * | I            | N            | W             |
 * | I            | O            | Y             |
 * | I            | P            | H             |
 * | I            | Q            | X             |
 * | I            | R            | U             |
 * | I            | S            | S             |
 * | I            | T            | P             |
 * | I            | U            | A             |
 * | I            | V            | I             |
 * | I            | W            | B             |
 * | I            | X            | R             |
 * | I            | Y            | C             |
 * | I            | Z            | J             |
 * | II           | A            | A             |
 * | II           | B            | J             |
 * | II           | C            | D             |
 * | II           | D            | K             |
 * | II           | E            | S             |
 * | II           | F            | I             |
 * | II           | G            | R             |
 * | II           | H            | U             |
 * | II           | I            | X             |
 * | II           | J            | B             |
 * | II           | K            | L             |
 * | II           | L            | H             |
 * | II           | M            | W             |
 * | II           | N            | T             |
 * | II           | O            | M             |
 * | II           | P            | C             |
 * | II           | Q            | Q             |
 * | II           | R            | G             |
 * | II           | S            | Z             |
 * | II           | T            | N             |
 * | II           | U            | P             |
 * | II           | V            | Y             |
 * | II           | W            | F             |
 * | II           | X            | V             |
 * | II           | Y            | O             |
 * | II           | Z            | E             |
 * | III          | A            | B             |
 * | III          | B            | D             |
 * | III          | C            | F             |
 * | III          | D            | H             |
 * | III          | E            | J             |
 * | III          | F            | L             |
 * | III          | G            | C             |
 * | III          | H            | P             |
 * | III          | I            | R             |
 * | III          | J            | T             |
 * | III          | K            | X             |
 * | III          | L            | V             |
 * | III          | M            | Z             |
 * | III          | N            | N             |
 * | III          | O            | Y             |
 * | III          | P            | E             |
 * | III          | Q            | I             |
 * | III          | R            | W             |
 * | III          | S            | G             |
 * | III          | T            | A             |
 * | III          | U            | K             |
 * | III          | V            | M             |
 * | III          | W            | U             |
 * | III          | X            | S             |
 * | III          | Y            | Q             |
 * | III          | Z            | O             |
 * | IV           | A            | E             |
 * | IV           | B            | S             |
 * | IV           | C            | O             |
 * | IV           | D            | V             |
 * | IV           | E            | P             |
 * | IV           | F            | Z             |
 * | IV           | G            | J             |
 * | IV           | H            | A             |
 * | IV           | I            | Y             |
 * | IV           | J            | Q             |
 * | IV           | K            | U             |
 * | IV           | L            | I             |
 * | IV           | M            | R             |
 * | IV           | N            | H             |
 * | IV           | O            | X             |
 * | IV           | P            | L             |
 * | IV           | Q            | N             |
 * | IV           | R            | F             |
 * | IV           | S            | T             |
 * | IV           | T            | G             |
 * | IV           | U            | K             |
 * | IV           | V            | D             |
 * | IV           | W            | C             |
 * | IV           | X            | M             |
 * | IV           | Y            | W             |
 * | IV           | Z            | B             |
 * | V            | A            | V             |
 * | V            | B            | Z             |
 * | V            | C            | B             |
 * | V            | D            | R             |
 * | V            | E            | G             |
 * | V            | F            | I             |
 * | V            | G            | T             |
 * | V            | H            | Y             |
 * | V            | I            | U             |
 * | V            | J            | P             |
 * | V            | K            | S             |
 * | V            | L            | D             |
 * | V            | M            | N             |
 * | V            | N            | H             |
 * | V            | O            | L             |
 * | V            | P            | X             |
 * | V            | Q            | A             |
 * | V            | R            | W             |
 * | V            | S            | M             |
 * | V            | T            | J             |
 * | V            | U            | Q             |
 * | V            | V            | O             |
 * | V            | W            | F             |
 * | V            | X            | E             |
 * | V            | Y            | C             |
 * | V            | Z            | K             |
 * |--------------|--------------|---------------|
 * </pre>
 * 
 * @author Sly Technologies Inc
 * @author repos@slytechs.com
 *
 */
public class Rotor {

    /**
     * Parses the input dial positions string and returns an array of integers representing the initial rotor positions.
     * 
     * @param dialPositions the input dial positions string (e.g., "A B C")
     * @return an array of integers representing the initial rotor positions
     * @throws IllegalArgumentException if the input string is invalid
     */
    public static int[] parseDialString(String dialPositions) {
        dialPositions = dialPositions.replaceAll("\\s+", "").toUpperCase();
        if (dialPositions.length() != 3)
            throw new IllegalArgumentException("expecting 3 initial rotor positions " + dialPositions);

        return new int[] {
            dialPositions.charAt(0) - 'A',
            dialPositions.charAt(1) - 'A',
            dialPositions.charAt(2) - 'A',
        };
    }

    /**
     * Parses the input dial positions character array and returns an array of integers representing the initial rotor positions.
     * 
     * @param dialPositions the input dial positions character array
     * @return an array of integers representing the initial rotor positions
     * @throws IllegalArgumentException if the input array is invalid
     */
    public static int[] parseDialCharacters(char[] dialPositions) {
        if (dialPositions.length != 3)
            throw new IllegalArgumentException("expecting 3 initial rotor positions " + Arrays.toString(dialPositions));

        return new int[] {
            dialPositions[0] - 'A',
            dialPositions[1] - 'A',
            dialPositions[2] - 'A',
        };
    }

    /**
     * Parses the input rotors string and returns an array of integers representing the rotor indexes.
     * 
     * @param rotorsRomanIndexes the input rotors string (e.g., "I II III")
     * @return an array of integers representing the rotor indexes
     * @throws IllegalArgumentException if the input string is invalid
     */
    public static int[] parseRotorsString(String rotorsRomanIndexes) {
        String[] c = rotorsRomanIndexes.split(" ");
        if (c.length != 3)
            throw new IllegalArgumentException("expecting 3 rotor indexes");

        int[] rotors = new int[3];
        for (int i = 0; i < rotors.length; i++) {
            switch (c[i]) {
                case "0":
                case "I":
                    rotors[i] = 0;
                    break;

                case "2":
                case "II":
                    rotors[i] = 1;
                    break;

                case "3":
                case "III":
                    rotors[i] = 2;
                    break;

                case "4":
                case "IV":
                    rotors[i] = 3;
                    break;

                case "5":
                case "V":
                    rotors[i] = 4;
                    break;
                default:
                    throw new IllegalArgumentException("invalid rotor roman index " + c[i]);
            }
        }

        return rotors;
    }

    /**
     * Rotor index for Rotor I.
     */
    public static final int ROTOR_I = 0;

    /**
     * Rotor index for Rotor II.
     */
    public static final int ROTOR_II = 1;

    /**
     * Rotor index for Rotor III.
     */
    public static final int ROTOR_III = 2;

    /**
     * Rotor index for Rotor IV.
     */
    public static final int ROTOR_IV = 3;

    /**
     * Rotor index for Rotor V.
     */
    public static final int ROTOR_V = 4;

    /**
     * The number of positions on the rotor.
     */
    public static final int ROTOR_POSITIONS = 26;

    /**
     * The notch positions for each rotor.
     */
    // @formatter:off
    public static final int[] ROTOR_NOTCHES = {
        16, // Rotor I: Notch at Q (17th position, 0-based index is 16)
        4,  // Rotor II: Notch at E (5th position, 0-based index is 4)
        21, // Rotor III: Notch at V (22nd position, 0-based index is 21)
        9,  // Rotor IV: Notch at J (10th position, 0-based index is 9)
        25  // Rotor V: Notch at Z (26th position, 0-based index is 25)
    };
    // @formatter:on

    /**
     * The wiring table for each rotor.
     */
    // @formatter:off
    public static final int[][] ROTORS = {
        // Rotor I
        {4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9},
        // Rotor II
        {0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4},
        // Rotor III
        {1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14},
        // Rotor IV
        {4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1},
        // Rotor V
        {21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 14, 5, 9, 2, 10, 4, 16}
    };
    // @formatter:on

    private final int[] table;
    private final int[] reverse;
    private final int notch; // Fixed notch or dial index on which it rotates the next rotor
    private int dial;
    private final Rotor nextRotor;

    /**
     * Constructs a new Rotor with the specified index.
     * 
     * @param index the 0-based index of the rotor (0 for Rotor I, 1 for Rotor II, etc.)
     */
    public Rotor(int index) {
        this(index, null);
    }

    /**
     * Constructs a new Rotor with the specified index and next rotor.
     * 
     * @param index the 0-based index of the rotor (0 for Rotor I, 1 for Rotor II, etc.)
     * @param nextRotor the next rotor in the sequence
     */
    public Rotor(int index, Rotor nextRotor) {
        this.nextRotor = nextRotor;
        Objects.checkIndex(index, 5);

        table = ROTORS[index];
        notch = ROTOR_NOTCHES[index];

        reverse = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            int ch = table[i];
            reverse[ch] = i;
        }
    }

    /**
     * Sets the dial position of the rotor.
     * 
     * @param dialPosition the dial position to set
     */
    public void setDial(int dialPosition) {
        dial = dialPosition;
    }

    /**
     * Rotates the rotor. If the rotor reaches its notch position, it rotates the next rotor in the sequence.
     */
    void rotate() {
        if (++dial == notch && nextRotor != null) {
            nextRotor.rotate();
        }

        dial %= ROTOR_POSITIONS;
    }

    /**
     * Processes the input character through the rotor in the forward direction.
     * 
     * @param ch the input character (as an integer)
     * @return the processed character (as an integer)
     */
    public int forward(int ch) {
        ch += dial;
        ch %= ROTOR_POSITIONS;

        ch = table[ch];

        return ch;
    }

    /**
     * Processes the input character through the rotor in the reverse direction.
     * 
     * @param ch the input character (as an integer)
     * @return the processed character (as an integer)
     */
    public int reverse(int ch) {
        ch += dial;
        ch %= ROTOR_POSITIONS;

        ch = reverse[ch];

        return ch;
    }
}
