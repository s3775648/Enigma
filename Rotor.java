/*
 * This is where Rotor objects are created and where all rotor related functions are kept.
 */
public class Rotor {

	private char[] alphabet;
	private char[] rotor_pins;

	public Rotor(char[] alphabet, char[] rotor_pins) {

		this.rotor_pins = rotor_pins;
		this.alphabet = alphabet;
	}

	// As this enigma machine uses letters, this is where the starting letter
	// position of the rotor is converted into an integer.
	public int findIndex(char rotor_position) {

		int index_no = 0;

		for (int i = 0; i < this.alphabet.length; i++) {
			int compare_chars = Character.compare(rotor_position, this.alphabet[i]);

			if (compare_chars == 0) {
				index_no = i;
			}
		}
		return index_no;
	}

	// The integer in the FindIndex function bove is used to set the starting
	// position of the rotor.
	public void shiftRotor(int movement_amount, boolean direction) {

		for (int i = 0; i < movement_amount; i++) {
			if (direction) {
				rotateRotor();
			} else {
				rotateRotorBackwards();
			}

		}
	}

	// This is where the rotation of the rotor actually takes place.
	public void rotateRotor() {

		char temp = this.alphabet[0];

		for (int i = 0; i < this.alphabet.length - 1; i++) {
			this.alphabet[i] = this.alphabet[i + 1];
		}

		this.alphabet[this.alphabet.length - 1] = temp;
	}

	// This is called for each char in the message and returns an encoded letter
	// each time.
	public char encrypt_message(char letter) {

		char encoded_letter = 0;

		for (int i = 0; i < this.rotor_pins.length; i++) {
			int compare_letters = Character.compare(letter, this.alphabet[i]);

			if (compare_letters == 0) {
				encoded_letter = this.rotor_pins[i];
			}
		}
		return encoded_letter;
	}

	// This is where the rotation of the rotor backwards takes place. This is used for decryption purposes.
	public void rotateRotorBackwards() {

		char temp = this.alphabet[this.alphabet.length - 1];

		for (int i = this.alphabet.length - 1; i > 0 - 1; i--) {
			this.alphabet[i] = this.alphabet[i - 1];
		}
		this.alphabet[0] = temp;
	}

	// This method decrypts a message sent through early enigma versions.
	public char decrypt_message(char letter) {

		char encoded_letter = 0;

		for (int i = 0; i < this.rotor_pins.length; i++) {
			int compare_letters = Character.compare(letter, this.rotor_pins[i]);

			if (compare_letters == 0) {
				encoded_letter = this.alphabet[i];
			}
		}
		return encoded_letter;
	}
	
}