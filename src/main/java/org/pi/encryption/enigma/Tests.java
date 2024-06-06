/*
 * MIT License
 * 
 * Copyright (c) 2020 Sly Technologies Inc.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.pi.encryption.enigma;

import java.util.Objects;
import java.util.function.IntUnaryOperator;

/**
 * Some basic functionality tests while developing the initial code.
 */
class Tests {
		private final IntUnaryOperator forward;
		private final IntUnaryOperator reverse;

		public Tests(IntUnaryOperator forward) {
			this.forward = Objects.requireNonNull(forward);
			this.reverse = null;
		}

		public Tests(IntUnaryOperator forward, IntUnaryOperator reverse) {
			this.forward = Objects.requireNonNull(forward);
			this.reverse = Objects.requireNonNull(reverse);
		}

		public char forward(char input) {
			int ch = forward.applyAsInt(input - 'A');

			return (char) ('A' + ch);
		}

		public char reverse(char input) {
			int ch = reverse.applyAsInt(input - 'A');

			return (char) ('A' + ch);
		}

		public static void test() {
			Plugboard pb = new Plugboard();
			pb.setPlugboard("SZ GT DV KU FO MY EW JN IX LQ");
			Tests pbP = new Tests(pb::exchange);

			char ch = 'Z';
			System.out.println();
			System.out.printf("Plugboard=%c%n", ch);
			System.out.printf("Plugboard=%c%n", ch = pbP.forward(ch));
			System.out.printf("Plugboard=%c%n", ch = pbP.forward(ch));

			Tests refP = new Tests(new Reflector(0)::reflect);

			Rotor rt = new Rotor(0);
			rt.setDial(0);
			Tests rtP = new Tests(rt::forward, rt::reverse);

			System.out.println();
			System.out.printf("Rotor1=%c%n", ch);
			System.out.printf("Rotor1=%c%n", ch = rtP.forward(ch));
//			System.out.printf("Reflect=%c%n", ch = refP.forward(ch));
//			System.out.printf("Rotor1=%c%n", ch = rtP.reverse(ch));

			rt.setDial(0);
			System.out.println();
			System.out.printf("Rotor2=%c%n", ch);
			System.out.printf("Rotor2=%c%n", ch = rtP.forward(ch));
//			System.out.printf("Reflect=%c%n", ch = refP.forward(ch));
//			System.out.printf("Rotor2=%c%n", ch = rtP.reverse(ch));
		}
	}