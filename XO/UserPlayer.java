package XO;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class UserPlayer extends Player implements Runnable {
	Scanner scanner = new Scanner(System.in);
	
	public UserPlayer(char playerType, Game game) {
		super(playerType, game);
	}
	
	public GameCoordinates coordToPlay(List<GameCoordinates> freeCells) {
		System.out.println("Enter row and column (separated by space): ");
		Scanner scanner = new Scanner(System.in);
		int row = -1;
		int col = -1;

		while (true) {
			try {
				row = scanner.nextInt();
				col = scanner.nextInt();
				
				if (row > 0 && row <= 5 && col > 0 && col <= 5 && game.isCellFree(row - 1, col - 1)) {
					break;
				} else {
					System.out.println("Invalid input. Try again.");
				}
			}
			
			catch (Exception e) {
				System.out.println("Invalid input. Try again.");
				scanner.nextLine();
			}
		}

		GameCoordinates playerChoice = new GameCoordinates(row - 1, col - 1);
		return playerChoice;
	}
	
	public synchronized void playTurn() {
		List<GameCoordinates> freeCells = game.getFreeCells();
		GameCoordinates playerChoice = coordToPlay(freeCells);
		game.placeXOinBoard(playerChoice, playerType);
	}
	
	public void run() {
	}
}
