package XO;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose the game type:");
		System.out.println("1. Computer vs. Computer");
		System.out.println("2. User vs. Computer");
		int choice = scanner.nextInt();

		if (choice == 1) {
			SelfGame selfGame = new SelfGame();
			selfGame.startGame();
		} else if (choice == 2) {
			UserGame userGame = new UserGame();
			userGame.startGame();
		} else {
			System.out.println("");
		}
	}
}