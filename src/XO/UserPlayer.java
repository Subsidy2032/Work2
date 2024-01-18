package XO;
import java.util.Scanner;

public class UserPlayer extends Player {
	public UserPlayer(char playerType, Game game){
		super(playerType, game);
	}
	Scanner scan = new Scanner(System.in);

	@Override
	public boolean isMyTurn() {
		return game.getTurn() == playerType;
	}

	@Override
	public synchronized void playTurn() {
		if (!isMyTurn()) {
			return;
		}

		System.out.println("Enter row and column (separated by space): ");
		Scanner scanner = new Scanner(System.in);
		int row = -1;
		int col = -1;

		while (true) {
			try {
				row = scanner.nextInt();
				col = scanner.nextInt();
				if (row >= 0 && row < 5 && col >= 0 && col < 5 && game.isCellFree(row, col)) {
					break;
				} else {
					System.out.println("Invalid input. Try again.");
				}
			} catch (Exception e) {
				System.out.println("Invalid input. Try again.");
				scanner.nextLine();
			}
		}

		GameCoordinates playerChoice = new GameCoordinates(row, col);
		game.placeXOinBoard(playerChoice, playerType);
	}

	
	public void run() {
	}
}
