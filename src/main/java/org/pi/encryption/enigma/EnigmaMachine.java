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

import static org.pi.encryption.enigma.Reflector.*;
import static org.pi.encryption.enigma.Rotor.*;

import java.util.Arrays;
import java.util.Objects;

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
//		var enigma = new EnigmaMachine("SZ GT DV KU FO MY EW JN IX LQ", "B", "I V III");
		var enigma = new EnigmaMachine("SZ GT DV KU FO MY EW JN IX LQ",
				REFLECTOR_B,
				ROTOR_I, ROTOR_V, ROTOR_III);

		String clear = "HELLO";
//		clear = escape(clear);
		System.out.println(" Clear: " + clear);

		enigma.setDials(14, 9, 24);
		var cypher = enigma.encrypt(clear);
		System.out.println("Cypher: " + cypher);

		enigma.setDials(14, 9, 24);
		clear = enigma.decrypt(cypher);
//		clear = unescape(clear);
		System.out.println(" Clear: " + clear);
	}

	private final Rotor rotor1;
	private final Rotor rotor2;
	private final Rotor rotor3;
	private final Reflector reflector;
	private final Plugboard plugboard;

	public static int[] parseRotorsString(String rotorsRomanIndexes) {
		String[] c = rotorsRomanIndexes.split(" ");
		if (c.length != 3)
			throw new IllegalArgumentException("expecting 3 rotor indexes");

		int[] rotors = new int[3];
		for (int i = 0; i < rotors.length; i++) {
			rotors[i] = switch (c[i]) {

			case "I" -> 0;
			case "II" -> 1;
			case "III" -> 2;
			case "IV" -> 3;
			case "V" -> 4;

			default -> throw new IllegalArgumentException("invalid rotor roman index " + c[i]);
			};
		}

		return rotors;
	}

	public static int parseReflectorIndex(String reflectorString) {
		return switch (reflectorString) {
		case "B" -> 0;
		case "C" -> 1;

		default -> throw new IllegalArgumentException("invalid reflector string");
		};
	}

	public EnigmaMachine(String plugboard, String reflectorString, String rotorsRomanIndexes) {
		this(plugboard, parseReflectorIndex(reflectorString), parseRotorsString(rotorsRomanIndexes));
	}

	public EnigmaMachine(String plugboard, int reflector, int... rotors) {
		this(new Plugboard(plugboard), new Reflector(reflector), rotors[0], rotors[1], rotors[2]);

		System.out.printf("plugboard=%s, reflector=%d rotors=%s%n",
				plugboard, reflector, Arrays.asList(rotors[0], rotors[1], rotors[2]).toString());

		if (rotors.length != 3)
			throw new IllegalArgumentException("expected 3 rotor indexes");
	}

	/**
	 * @param plugboard
	 * @param reflector
	 * @param rotor1
	 * @param rotor2
	 * @param rotor3
	 */
	public EnigmaMachine(Plugboard plugboard, Reflector reflector, int r1, int r2, int r3) {

		this.plugboard = Objects.requireNonNull(plugboard, "plugboard");
		this.reflector = Objects.requireNonNull(reflector, "reflector");
		this.rotor3 = new Rotor(r3);
		this.rotor2 = new Rotor(r2, rotor3);
		this.rotor1 = new Rotor(r1, rotor2);

		reset();
	}

	public void reset() {
		setDials(0, 0, 0);
	}

	public void setDials(int r1, int r2, int r3) {
		rotor1.setDial(r1);
		rotor2.setDial(r2);
		rotor3.setDial(r3);
	}

	public int process(int ascii) {
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

	public char applyChar(char ch) {
		ch -= 'A';

		ch = (char) process(ch);

		ch += 'A';
		return ch;
	}

	private static String escape(String text) {

		StringBuilder b = new StringBuilder(text.length() + 12);
		text = text.toUpperCase();
		final int len = text.length();

		for (int i = 0; i < len; i++) {
			char ch = text.charAt(i);
			switch (ch) {
			case '.' -> b.append("XX");
			case ',' -> b.append("YY");
			case '?' -> b.append("ZZ");
			case '!' -> b.append("JC");
			case ':' -> b.append("JA");
			case ';' -> b.append("JB");
			case ' ' -> b.append("QQ");

			case '0' -> b.append("QZ");
			case '1' -> b.append("AA");
			case '2' -> b.append("BB");
			case '3' -> b.append("CC");
			case '4' -> b.append("DD");
			case '5' -> b.append("EE");
			case '6' -> b.append("FF");
			case '7' -> b.append("GG");
			case '8' -> b.append("HH");
			case '9' -> b.append("II");

			default -> b.append(ch);
			};
		}

		return b.toString();
	}

	private String unescape(String text) {

		StringBuilder b = new StringBuilder(text.length() + 12);
		text = text.toUpperCase();
		final int len = text.length();

		for (int i = 0; i < len; i++) {
			char ch = text.charAt(i);
			switch (ch) {
			case '.' -> b.append("XX");
			case ',' -> b.append("YY");
			case '?' -> b.append("ZZ");
			case '!' -> b.append("JC");
			case ':' -> b.append("JA");
			case ';' -> b.append("JB");
			case ' ' -> b.append("QQ");

			default -> b.append(ch);
			};
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
