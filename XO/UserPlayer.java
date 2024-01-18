package XO;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * A class for user player (user)
 * @author Ron Bitan (315924316) && Noam Muchink (212472484)
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
		System.out.println("Enter row and column between 1-5 (separated by space): ");
		int row;
		int col;

		while (true) {
			// Get the row and column that the user wants to play and handle exceptions
			try {
				row = scanner.nextInt();
				col = scanner.nextInt();
				
				if (row > 0 && row <= 5 && col > 0 && col <= 5 && game.isCellFree(row - 1, col - 1)) {
					break;
				} else {
					throw new IllegalArgumentException("The cell must be free, and the numbers should be between 1 to 5");
				}
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
	public synchronized void playTurn() {
		try {
			// The player sleeps for 500ms any time it's not its turn, and exits the function if the board is full
			while(!isMyTurn()) {
				Thread.sleep(500);
				if(game.isBoardFull()) {
					if(playerType == 'X')
						System.out.println("Board is full");
					setKeepPlaying(false);
					return;
				}
				
			}
		}
		
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		// Checks if the other player won and exists the function if he did
		if(game.isWinner(playerType == 'X' ? new SelfPlayer('O', game):new SelfPlayer('X', game))) {
			setKeepPlaying(false);
			return;
		}
		
		// Puts the player type's on the board in the coordinate he chose to play
		GameCoordinates playerChoice = coordToPlay();
		game.placeXOinBoard(playerChoice, playerType);
		
		// Checks if the current player is a winner, exists the function if he is
		if(game.isWinner(this)) {
			setKeepPlaying(false);
			return;
		}
		
		// Prints the board and set the turn to the other player
		game.printBoard();
		game.setTurn(this);
	}
	
	/**
	 * Runs the thread
	 */
	public void run() {
		setKeepPlaying(true);
		
		while(getKeepPlaying()) {
			playTurn();
		}
		
		scanner.close();
	}
}
