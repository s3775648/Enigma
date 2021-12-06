import java.util.Scanner;

/* 
 * This Program attempts to recreate the enigma machine used in Germany before and during the war. The enigma machine
 * encrypted messages used by the German Wehrmacht as well as by industry.
*/

public class Main {

	private static Rotor[] rotors;
	private static Scanner sc = new Scanner(System.in);
	private static boolean direction = true;

	public static void main(String[] args) {

		rotors = new Rotor[3];
		addRotors();

		String selection;

		do {

			System.out.println("     ***** ENIGMA *****     ");
			System.out.println("A. Set Rotor Positions");
			System.out.println("B. Encrypt A Message");
			System.out.println(
					"C. Decrypt A Message (Ensure Correct Rotor Settings First)");
			System.out.println("X. Exit the Program");
			System.out.println();

			System.out.print("Enter Selection: ");
			selection = sc.nextLine();

			System.out.println();

			if (selection.length() != 1) {
				System.out.print("Error - Invalid Selection!");
			} else {

				switch (selection.toUpperCase()) {
				case "A":
					setRotorPosition();
					break;

				case "B":
					encryptMessage();
					break;

				case "C":
					decryptMessage();
					break;

				case "X":
					System.out.println("Exiting the Program...");
					break;

				default:
					System.out.println("Error - Invalid Selection!");
				}
			}
			System.out.println();

		} while (!selection.equalsIgnoreCase("X"));
	}

	// User is asked to input a message and then encrypts the message and returns
	// the encrypted message
	private static void encryptMessage() {

		System.out.println("What is the message to be encrypted: ");
		String input = sc.nextLine();

		String message = input.toLowerCase();

		String encrypted_message = "";

		for (int i = 0; i < message.length(); i++) {
			char temp = message.charAt(i);
			char encoded_char = rotors[0].encrypt_message(temp);

			encrypted_message += encoded_char;
			rotors[0].rotateRotor();
		}
		System.out
				.println("The Encrypted Message is : " + encrypted_message.toUpperCase());
	}

	
	// User is asked to input a message and then encrypts the message and returns
	// the encrypted message
	private static void decryptMessage() {

		System.out.println("What is the message to be decrypted: ");
		String input = sc.nextLine();

		String message = input.toLowerCase();

		String decrypted_message = "";

		for (int i = 0; i < message.length(); i++) {
			char temp = message.charAt(i);
			char encoded_char = rotors[0].decrypt_message(temp);

			decrypted_message += encoded_char;
			rotors[0].rotateRotor();
		}
		System.out
				.println("The Deacrypted Message is : " + decrypted_message.toUpperCase());
	}

	
	// This function sets the starting rotor positions by asking for them from the
	// user
	private static void setRotorPosition() {
		
		direction = true;
		
		System.out.println("Enter the starting position of Rotor 1? [a-z]");
		char letter = sc.next().charAt(0);
		sc.nextLine();

		char position = Character.toLowerCase(letter);

		int starting_position = rotors[0].findIndex(position);

		rotors[0].shiftRotor(starting_position, direction);
	}

	// This is where the rotor objects are added
	private static void addRotors() {

		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] rotor_one_pins = "uhlptcjwmnafqiydsogxebzrkv".toCharArray();
		char[] rotor_two_pins = "rjwitufnqdbolazhspxkvemcgy".toCharArray();

		rotors[0] = new Rotor(alphabet, rotor_one_pins);
	}
}
