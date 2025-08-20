package XO;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	public static void main(String[] args) {
		// Asks from the user the type of game he wants to play
		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose the game type:");
		System.out.println("1. Computer vs. Computer");
		System.out.println("2. User vs. Computer");
		int choice;
		
		// Runs until the user enters a valid choice and handles exceptions
		while(true) {
			try {
				choice = scanner.nextInt();
				
				if(choice != 1 && choice != 2)
					throw new IllegalArgumentException("Choice must be 1 or 2");
				
				else
					break;
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				scanner.nextLine();
			}
			
			catch(InputMismatchException e) {
				System.out.println("Invalid input. Try again.");
				scanner.nextLine();
			}
		} // End of while loop
		
		// If the user chose to play a self game, creates a self game object and starts the game
		if (choice == 1) {
			SelfGame selfGame = new SelfGame();
			selfGame.startGame();
		}
		
		// If the user chose to play a user game asks him with which char he wants to play (X or O)
		else if (choice == 2) {
			System.out.println("Choose what type you want to play with (X or O): ");
			char type = '\u0000';
			
			// Runs until the user enters a valid choice and handles exceptions
			while(true) {
				try {
					type = scanner.next().charAt(0);
					
					if(type != 'X' && type != 'O')
						throw new IllegalArgumentException("Input must be X or O try again");
					
					else
						break;
				}
				catch(IllegalArgumentException e) {
					System.out.println(e.getMessage());
					scanner.nextLine();
				}
				
				catch(InputMismatchException e) {
					System.out.println("Invalid input. Try again.");
					scanner.nextLine();
				}
			} // End of while loop
			
			// If the user chose to play a user game, creates a user game object with the type the user chose and starts the game
			UserGame userGame = new UserGame(type);
			userGame.startGame();
		}
		
		scanner.close();
	}
}