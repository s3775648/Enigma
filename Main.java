import java.util.Scanner;
/* 
 * This Program attempts to recreate the enigma machine used in Germany before and during the war. The enigma machine
 * encrypted messages used by the German Wehrmacht as well as by industry.
 * 
 * Milestones hit: First rotor completed 
 * 
 * Goals: 	Send a custom message and recieve encoded output.
 * 			Add a further two rotors to the machine.
 * 			Be able to change the position of the rotors.
 *			Add a further 2 rotors that can be swapped in for others.
 *			Add a reflector at the end of the rotors.
 *			Add a plug board to the enigma machine
 *
 *Some resources that are helpful to understanding enigma -
 *
 *	https://web.stanford.edu/class/cs106j/handouts/36-TheEnigmaMachine.pdf
 *	https://www.youtube.com/watch?v=QwQVMqfoB2E
 */			 		 	
 
public class Main {

	private static Rotor[] rotors;
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
	

		rotors = new Rotor[3];

		addRotors();

		System.out.println("Enter the starting position of Rotor 1? [a-z]");
		char letter = sc.next().charAt(0);

		char position = Character.toLowerCase(letter);

		int starting_position = rotors[0].findIndex(position);
		
		rotors[0].shiftRotor(starting_position);
		
		// TESTING
		char message = 'b';

		char encrypted_char = rotors[0].encrypt_message(message);

		System.out.print(encrypted_char);
	}
	
	// This is where the rotor objects are added
	private static void addRotors() {

		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] rotor_one_pins = "uhlptcjwmnafqiydsogxebzrkv".toCharArray();

		rotors[0] = new Rotor(alphabet, rotor_one_pins);
	}
}
