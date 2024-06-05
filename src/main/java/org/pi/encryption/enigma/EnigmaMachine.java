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

import java.util.stream.IntStream;

/**
 * Enigma machine implementation. The data path is where each character
 * traverses through different transformations from keyboard presses to where
 * the encrypted character is displayed on the light board.
 * 
 * The cypher is bidirectional, meaning it is used to encrypt and decrypt a
 * character. For example {@code A -> B} and {@code B -> A}.
 * 
 * <p>
 * This is a list of all of the transformation steps
 * </p>
 * 
 * <pre>
 * [keyboard] 
 *   -> [plugboard] 
 *     -> [rotor1] 
 *       -> [rotor2] 
 *         -> [rotor3]
 *           <-> [reflector] 
 *         <- [rotor3] 
 *       <- [rotor2] 
 *     <- [rotor1] 
 *   <- [plugboard]
 * [light display]
 * </pre>
 * 
 * @version 1.0
 * @since 2023
 */
public class EnigmaMachine {

    /**
     * Escapes the input text by replacing certain characters with specific two-character pairs.
     *
     * @param text the input text to be escaped
     * @return the escaped text
     */
    public static String escape(String text) {
        StringBuilder b = new StringBuilder(text.length() + 12);
        text = text.toUpperCase();
        final int len = text.length();

        for (int i = 0; i < len; i++) {
            char ch = text.charAt(i);
            switch (ch) {
                case '.':
                    b.append("XX");
                    break;
                case ',':
                    b.append("YY");
                    break;
                case '?':
                    b.append("ZZ");
                    break;
                case '!':
                    b.append("JC");
                    break;
                case ':':
                    b.append("JA");
                    break;
                case ';':
                    b.append("JB");
                    break;
                case ' ':
                    b.append("QQ");
                    break;
                case '0':
                    b.append("QZ");
                    break;
                case '1':
                    b.append("AA");
                    break;
                case '2':
                    b.append("BB");
                    break;
                case '3':
                    b.append("CC");
                    break;
                case '4':
                    b.append("DD");
                    break;
                case '5':
                    b.append("EE");
                    break;
                case '6':
                    b.append("FF");
                    break;
                case '7':
                    b.append("GG");
                    break;
                case '8':
                    b.append("HH");
                    break;
                case '9':
                    b.append("II");
                    break;
                default:
                    b.append(ch);
                    break;
            }
        }

        return b.toString();
    }

    /**
     * The main method to demonstrate the Enigma machine encryption and decryption process.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        EnigmaMachine enigma = new EnigmaMachine();

        enigma.setRotors("I II IV");
        enigma.setReflector("B");
        enigma.setPlugboard("SZ GT DV KU FO MY EW JN IX LQ");
        enigma.setInitialPositions('A', 'B', 'C');

        String clear = "HELLO";
        clear = EnigmaMachine.escape(clear);
        System.out.println(" Clear: " + clear);

        var cypher = enigma.encrypt(clear);
        System.out.println("Cypher: " + cypher);

        // Reset to the same positions for decryption
        enigma.setInitialPositions("A B C");
        clear = enigma.decrypt(cypher);
        clear = EnigmaMachine.unescape(clear);
        System.out.println(" Clear: " + clear);
    }

    /**
     * Unescapes the input text by converting two-character pairs back into the corresponding single character.
     *
     * @param text the input text to be unescaped
     * @return the unescaped text
     */
    public static String unescape(String text) {
        StringBuilder b = new StringBuilder(text.length());
        final int len = text.length();

        for (int i = 0; i < len; i++) {
            char ch = text.charAt(i);
            if (i + 1 < len) {
                char nextCh = text.charAt(i + 1);
                if (ch == 'X' && nextCh == 'X') {
                    b.append('.');
                    i++;
                } else if (ch == 'Y' && nextCh == 'Y') {
                    b.append(',');
                    i++;
                } else if (ch == 'Z' && nextCh == 'Z') {
                    b.append('?');
                    i++;
                } else if (ch == 'J' && nextCh == 'C') {
                    b.append('!');
                    i++;
                } else if (ch == 'J' && nextCh == 'A') {
                    b.append(':');
                    i++;
                } else if (ch == 'J' && nextCh == 'B') {
                    b.append(';');
                    i++;
                } else if (ch == 'Q' && nextCh == 'Q') {
                    b.append(' ');
                    i++;
                } else if (ch == 'Q' && nextCh == 'Z') {
                    b.append('0');
                    i++;
                } else if (ch == 'A' && nextCh == 'A') {
                    b.append('1');
                    i++;
                } else if (ch == 'B' && nextCh == 'B') {
                    b.append('2');
                    i++;
                } else if (ch == 'C' && nextCh == 'C') {
                    b.append('3');
                    i++;
                } else if (ch == 'D' && nextCh == 'D') {
                    b.append('4');
                    i++;
                } else if (ch == 'E' && nextCh == 'E') {
                    b.append('5');
                    i++;
                } else if (ch == 'F' && nextCh == 'F') {
                    b.append('6');
                    i++;
                } else if (ch == 'G' && nextCh == 'G') {
                    b.append('7');
                    i++;
                } else if (ch == 'H' && nextCh == 'H') {
                    b.append('8');
                    i++;
                } else if (ch == 'I' && nextCh == 'I') {
                    b.append('9');
                    i++;
                } else {
                    b.append(ch);
                }
            } else {
                b.append(ch);
            }
        }

        return b.toString();
    }

    private Rotor rotor1;
    private Rotor rotor2;
    private Rotor rotor3;
    private Reflector reflector;
    private final Plugboard plugboard;
    private int[] initialPositions;

    /**
     * Constructor to initialize the Enigma machine with default settings.
     */
    public EnigmaMachine() {
        this.plugboard = new Plugboard();
        this.reflector = new Reflector(Reflector.REFLECTOR_B);
        this.initialPositions = new int[]{0, 0, 0};

        setRotors(0, 1, 2);
    }

    /**
     * Applies the encryption or decryption process to the input text.
     *
     * @param text the input text to be processed
     * @return the processed text
     */
    private String apply(String text) {
        final int len = text.length();
        StringBuilder b = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            char ch = text.charAt(i);

            ch = applyChar(ch);

            b.append(ch);
        }

        return b.toString();
    }

    /**
     * Applies the encryption or decryption process to a single character.
     *
     * @param ch the input character to be processed
     * @return the processed character
     */
    private char applyChar(char ch) {
        ch -= 'A';

        ch = (char) process(ch);

        ch += 'A';
        return ch;
    }

    /**
     * Decrypts the input cypher text.
     *
     * @param cypherText the input cypher text to be decrypted
     * @return the decrypted text
     */
    public String decrypt(String cypherText) {
        return apply(cypherText);
    }

    /**
     * Encrypts the input clear text.
     *
     * @param clearText the input clear text to be encrypted
     * @return the encrypted text
     */
    public String encrypt(String clearText) {
        return apply(clearText);
    }

    /**
     * Processes the ASCII value through the Enigma machine's components.
     *
     * @param ascii the input ASCII value to be processed
     * @return the processed ASCII value
     */
    private int process(int ascii) {
        rotor1.rotate();

        ascii = plugboard.exchange(ascii);
        ascii = rotor1.forward(ascii);
        ascii = rotor2.forward(ascii);
        ascii = rotor3.forward(ascii);
        ascii = reflector.reflect(ascii);
        ascii = rotor3.reverse(ascii);
        ascii = rotor2.reverse(ascii);
        ascii = rotor1.reverse(ascii);
        ascii = plugboard.exchange(ascii);

        return ascii;
    }

    /**
     * Sets the initial positions of the rotors using character values.
     *
     * @param initialLetterPositions the initial positions of the rotors
     */
    public void setInitialPositions(char... initialLetterPositions) {
        setInitialPositions(Rotor.parseDialCharacters(initialLetterPositions));
    }

    /**
     * Sets the initial positions of the rotors using integer values.
     *
     * @param initialLetterPositions the initial positions of the rotors
     */
    public void setInitialPositions(int... initialLetterPositions) {
        if (initialLetterPositions.length != 3) {
            throw new IllegalArgumentException("expected 3 positions for the 3 rotors");
        }

        this.initialPositions = initialLetterPositions;

        rotor1.setDial(this.initialPositions[0]);
        rotor2.setDial(this.initialPositions[1]);
        rotor3.setDial(this.initialPositions[2]);
    }

    /**
     * Sets the initial positions of the rotors using a string.
     *
     * @param initialLetterPositions the initial positions of the rotors
     */
    public void setInitialPositions(String initialLetterPositions) {
        initialLetterPositions = initialLetterPositions.replaceAll("\\s+", "");
        if (initialLetterPositions.length() != 3)
            throw new IllegalArgumentException("expecting 3 rotor initial letter positions " + initialLetterPositions);

        setInitialPositions(
                initialLetterPositions.charAt(0),
                initialLetterPositions.charAt(1),
                initialLetterPositions.charAt(2));
    }

    /**
     * Sets the plugboard configuration using a string of letter pairs.
     *
     * @param plugBoardPairs the plugboard configuration
     */
    public void setPlugboard(String plugBoardPairs) {
        plugboard.setPlugboard(plugBoardPairs);
    }

    /**
     * Sets the reflector configuration using a string.
     *
     * @param reflectorLetterBorC the reflector configuration
     */
    public void setReflector(String reflectorLetterBorC) {
        switch (reflectorLetterBorC.toUpperCase()) {
            case "B":
                this.reflector = new Reflector(Reflector.REFLECTOR_B);
                break;
            case "C":
                this.reflector = new Reflector(Reflector.REFLECTOR_C);
                break;

            default:
                throw new IllegalArgumentException("invalid reflector letter " + reflectorLetterBorC);
        }
    }

    /**
     * Sets the rotors configuration using an array of rotor numbers.
     *
     * @param rotorNumbers the rotor configuration
     */
    public void setRotors(int... rotorNumbers) {
        if (rotorNumbers.length != 3)
            throw new IllegalArgumentException("expected 3 rotor numbers");

        long validatedRotors = IntStream.of(rotorNumbers)
                .filter(i -> i >= 0 && i < 5)
                .count();
        if (validatedRotors != 3)
            throw new IllegalArgumentException("rotor numbers must be between 0 and 4 inclusive");

        this.rotor3 = new Rotor(rotorNumbers[2]);
        this.rotor2 = new Rotor(rotorNumbers[1], rotor3);
        this.rotor1 = new Rotor(rotorNumbers[0], rotor2);

        setInitialPositions(initialPositions);
    }

    /**
     * Sets the rotors configuration using a string of rotor numbers.
     *
     * @param rotoNumbers the rotor configuration
     */
    public void setRotors(String rotoNumbers) {
        int[] rotorNos = Rotor.parseRotorsString(rotoNumbers);

        setRotors(rotorNos);
    }
}
