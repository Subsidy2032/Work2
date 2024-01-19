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
		int row;
		int col;
		
		System.out.print("Enter row to play (1-5): ");
		
		while (true) {
			// Get the row and column that the user wants to play and handle exceptions
			try {
				row = scanner.nextInt();
				
				if (row < 0 || row > 5)
					throw new IllegalArgumentException("The raw number should be between 1 to 5! try again");
					
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
		
		System.out.print("Enter column to play (1-5): ");
		
		while (true) {
			// Get the row and column that the user wants to play and handle exceptions
			try {
				col = scanner.nextInt();
				
				if (col < 0 || col > 5)
					throw new IllegalArgumentException("The column number should be between 1 to 5! try again.");
				
				else if (!game.isCellFree(row - 1, col - 1))
					throw new IllegalArgumentException("The cell must be free! try again.");
				
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
		synchronized(LOCK) {
			try {
				// The player waits for his turn
				while(!isMyTurn()) {
					LOCK.wait();
				}
			}
			
			catch(InterruptedException e) {
			}
			
			// Checks if the other player won and exists the function if he did
			if(game.isWinner(playerType == 'X' ? new SelfPlayer('O', game):new SelfPlayer('X', game))) {
				setKeepPlaying(false);
				return;
			}
			
			// The player terminates the program if the board is full
			if(game.isBoardFull()) {
				System.out.println("Board is full");
				System.exit(0);
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
			// The player that just played notifies the other player that it's his turn
			LOCK.notifyAll();
		}
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
