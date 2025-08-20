package XO;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * A class for user player (user)
 *
 */
public class UserPlayer extends Player implements Runnable {
	Scanner scanner = new Scanner(System.in);
	
	/**
	 * 
	 * @param playerType The type to play
	 * @param game The game the user is part of
	 */
	public UserPlayer(char playerType, Game game) {
		super(playerType, game);
	}
	
	/**
	 * This method lets the user choose what coordinate he wants to play
	 * @return The coordinate the user chose to play
	 */
	public GameCoordinates coordToPlay() {
		int row = 0;
		int col = 0;
		boolean cellFree = true;
		
		while (true) {
			try {
				if(row < 1 || row > 5 || !cellFree) {
					cellFree = true;
					System.out.print("Enter row to play (1-5): ");
					row = scanner.nextInt();
					
					if (row < 1 || row > 5)
						throw new IllegalArgumentException("The row number should be between 1 to 5! try again");
				}
				
				System.out.print("Enter column to play (1-5): ");
				col = scanner.nextInt();
				
				if (col < 1 || col > 5)
					throw new IllegalArgumentException("The column number should be between 1 to 5! try again.");
				
				else if (!game.isCellFree(row - 1, col - 1)) {
					cellFree = false;
					throw new IllegalArgumentException("The cell must be free! try again.");
				}
				
				else
					break;
			}
			
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				scanner.nextLine();
			}
			
			catch(InputMismatchException e) {
				System.out.println("Invalid input. Try again.");
				scanner.nextLine();
			}
		}
		
		System.out.println();
		GameCoordinates playerChoice = new GameCoordinates(row - 1, col - 1);
		return playerChoice;
	}
	
	/**
	 * A method to play a turn
	 */
	public void playTurn() {
		synchronized(game) {
			try {
				// The player waits for his turn
				while(!isMyTurn()) {
					game.wait();
				}
			}
			
			catch(InterruptedException e) {
			}
			
			// Checks if the other player won and exists the function if he did
			if(game.isBoardFull() || !game.getKeepPlaying()) {
				game.setKeepPlaying(false);
				return;
			}
			
			// Puts the player type's on the board in the coordinate he chose to play, and set the turn to the other player
			GameCoordinates playerChoice = coordToPlay();
			game.placeXOinBoard(playerChoice, playerType);
			game.setTurn(this);
			
			// Checks if the current player is a winner, exists the function if he is
			if(game.isWinner(this)) {
				game.setKeepPlaying(false);
				return;
			}
			
			// Prints the board and set the turn to the other player
			game.printBoard();
			// The player that just played notifies the other player that it's his turn
			game.notifyAll();
		}
	}
	
	/**
	 * Runs the thread
	 */
	public void run() {
		game.setKeepPlaying(true);
		
		while(game.getKeepPlaying()) {
			playTurn();
			try {
				// The player sleeps for 500ms after his turn
				Thread.sleep(500);
			}
			
			catch(InterruptedException e) {}
		}
		
		scanner.close();
	}
}
