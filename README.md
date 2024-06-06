# EnigmaMachine
<img src="https://github.com/SecureNetWizard/enigma-machine/assets/166327546/9d22d69d-8d16-45ed-906e-d14e4c52c2b3)" alt="Logo" width="150" height="150">

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
  - [Rotors](#rotors)
  - [Reflector](#reflector)
  - [Plugboard](#plugboard)
  - [Initial Positions](#initial-positions)
- [Examples](#examples)
- [Running the Main Method](#running-the-main-method)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

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
    git clone https://github.com/yourusername/enigma-machine.git
    cd enigma-machine
    ```

2. **Build the project:**
    ```sh
    mvn clean install
    ```

### Usage

1. **Initialize the Enigma Machine:**

    ```java
    EnigmaMachine enigma = new EnigmaMachine();
    enigma.setRotors(1, 2, 3); // Example rotor configuration (3 out of 5)
    enigma.setReflector("B"); // Example reflector type (B or C)
    enigma.setPlugboard("SZ GT DV KU FO MY EW JN IX LQ"); // Example plugboard settings (10 letter pairs)
    enigma.setInitialPositions('A', 'A', 'A'); // Example initial positions
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
        enigma.setRotors(1, 2, 3);
        enigma.setReflector("B");
        enigma.setPlugboard("AB CD EF");
        enigma.setInitialPositions('A', 'A', 'A');

        String encryptedMessage = enigma.encrypt("HELLO WORLD");
        System.out.println("Encrypted: " + encryptedMessage);

        enigma.setInitialPositions('A', 'A', 'A');
        String decryptedMessage = enigma.decrypt(encryptedMessage);
        System.out.println("Decrypted: " + decryptedMessage);
    }
}
```
## Running the Main Method
You can run the EnigmaMachine.main method using Maven. Make sure you have followed the installation steps before running the main method.

To run the main method, use the following command:
```
mvn exec:java
```

## Contributing
Contributions are welcome! Please fork this repository and submit pull requests for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgements
This project is inspired by the historical Enigma machine and the many cryptographic enthusiasts who keep its legacy alive.
