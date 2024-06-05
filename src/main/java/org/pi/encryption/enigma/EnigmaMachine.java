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
 * @author Sly Technologies Inc
 * @author repos@slytechs.com
 */
public class EnigmaMachine {

	public static void main(String[] args) {
		EnigmaMachine enigma = new EnigmaMachine();

		enigma.setRotors(1, 2, 3);
		enigma.setReflector("B");
		enigma.setPlugboard("SZ GT DV KU FO MY EW JN IX LQ");
		enigma.setInitialPositions('A', 'B', 'C');

		String clear = "HELLO";
		clear = enigma.escape(clear);
		System.out.println(" Clear: " + clear);

		enigma.setInitialPositions(14, 9, 24);
		var cypher = enigma.encrypt(clear);
		System.out.println("Cypher: " + cypher);

		enigma.setInitialPositions(14, 9, 24);
		clear = enigma.decrypt(cypher);
		clear = enigma.unescape(clear);
		System.out.println(" Clear: " + clear);
	}

	private Rotor rotor1;
	private Rotor rotor2;
	private Rotor rotor3;
	private Reflector reflector;
	private final Plugboard plugboard;
	private int[] initialPositions;

	public EnigmaMachine() {
		this.plugboard = new Plugboard();
		this.reflector = new Reflector(Reflector.REFLECTOR_B);
		this.initialPositions = new int[] { 0,
				0,
				0 };

		setRotors(0, 1, 2);
	}

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

	public void setPlugboard(String plugBoardPairs) {
		plugboard.setPlugboard(plugBoardPairs);
	}

	public void setInitialPositions(char... initialLetterPositions) {
		setInitialPositions(Rotor.parseDialCharacters(initialLetterPositions));
	}

	public void setInitialPositions(int... initialLetterPositions) {
		if (initialLetterPositions.length != 3) {
			throw new IllegalArgumentException("expected 3 positions for the 3 rotors");
		}

		this.initialPositions = initialLetterPositions;

		rotor1.setDial(this.initialPositions[0]);
		rotor2.setDial(this.initialPositions[1]);
		rotor3.setDial(this.initialPositions[2]);
	}

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

	private char applyChar(char ch) {
		ch -= 'A';

		ch = (char) process(ch);

		ch += 'A';
		return ch;
	}

	public String escape(String text) {
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

	public String unescape(String text) {
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

	public String encrypt(String clearText) {
		return apply(clearText);
	}

	public String decrypt(String cypherText) {
		return apply(cypherText);
	}
}
