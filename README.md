# EnigmaMachine

## Overview

The `EnigmaMachine` is a Java software implementation of the historical Enigma machine used for encryption and decryption during World War II. Just like the physical Enigma machine, this software version includes dials, rotors, a reflector, and a plugboard, each of which must be initialized correctly to encrypt or decrypt messages.

## Features

- **Rotors**: Simulate the rotating disks that determine the substitution pattern for each letter.
- **Reflector**: Reflects the signal back through the rotors for additional scrambling.
- **Plugboard**: Allows for swapping pairs of letters for an extra layer of encryption.
- **Dials**: Set the initial position of each rotor for both encryption and decryption.

## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java 11 or higher
- Maven (for building the project)

### Installation

1. **Clone the repository:**
    ```sh
    git clone https://github.com/yourusername/EnigmaMachine.git
    cd EnigmaMachine
    ```

2. **Build the project:**
    ```sh
    mvn clean install
    ```

### Usage

1. **Initialize the Enigma Machine:**

    ```java
    EnigmaMachine enigma = new EnigmaMachine();
    enigma.setRotors(new int[]{1, 2, 3}); // Example rotor configuration
    enigma.setReflector("B"); // Example reflector type
    enigma.setPlugboard("AB CD EF"); // Example plugboard settings
    enigma.setInitialPositions(new char[]{'A', 'A', 'A'}); // Example initial positions
    ```

2. **Encrypt a message:**

    ```java
    String encryptedMessage = enigma.encrypt("HELLO WORLD");
    System.out.println("Encrypted: " + encryptedMessage);
    ```

3. **Decrypt a message:**

    Ensure the Enigma machine is initialized with the same settings used for encryption.

    ```java
    String decryptedMessage = enigma.decrypt(encryptedMessage);
    System.out.println("Decrypted: " + decryptedMessage);
    ```

## Configuration

### Rotors

Configure the rotors by specifying their types and initial positions. Example rotor types include I, II, III, IV, and V.

### Reflector

Set the reflector type, such as "A", "B", or "C".

### Plugboard

Specify the plugboard settings as pairs of letters to be swapped. Example: "AB CD EF" swaps A with B, C with D, and E with F.

### Initial Positions

Set the initial positions of the rotors. Example: `new char[]{'A', 'A', 'A'}` sets all rotors to position A.

## Examples

### Example Initialization and Encryption

```java
public class Main {
    public static void main(String[] args) {
        EnigmaMachine enigma = new EnigmaMachine();
        enigma.setRotors(new int[]{1, 2, 3});
        enigma.setReflector("B");
        enigma.setPlugboard("AB CD EF");
        enigma.setInitialPositions(new char[]{'A', 'A', 'A'});

        String encryptedMessage = enigma.encrypt("HELLO WORLD");
        System.out.println("Encrypted: " + encryptedMessage);

        enigma.setInitialPositions(new char[]{'A', 'A', 'A'});
        String decryptedMessage = enigma.decrypt(encryptedMessage);
        System.out.println("Decrypted: " + decryptedMessage);
    }
}
