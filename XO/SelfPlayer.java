package XO;
import java.util.List;
import java.util.Random;

/**
 * A class for self player (computer)
 * @author Ron Bitan (315924316) && Noam Muchink (212472484)
 *
 */
public class SelfPlayer extends Player implements Runnable {
	/**
	 * 
	 * @param playerType The type to play
	 * @param game The game the user is part of
	 */
	public SelfPlayer(char playerType, Game game) {
		super(playerType, game);
	}
	
	/**
	 * A method to generate a random cell in the game board's array, from a list of all free cells
	 * @param freeCells The list of free cells
	 * @return One of the free cells in the list
	 */
	public GameCoordinates getRandomCell(List<GameCoordinates> freeCells) {
		Random generator = new Random();
		int randomIndex = generator.nextInt(freeCells.size());
		return freeCells.get(randomIndex);
	}
	
	
	/**
	 * A method to play a turn
	 */
	@Override
	public void playTurn() {
		synchronized(LOCK) {
			try {
				// The player waits for his turn
				while(!isMyTurn()) {
					LOCK.wait();
				}
			}
			
			catch(InterruptedException e) {}
			
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
			
			// Places the player type char's on a random cell in the board
			List<GameCoordinates> freeCells = game.getFreeCells();
			GameCoordinates randomCoordinate = getRandomCell(freeCells);
			game.placeXOinBoard(randomCoordinate, playerType);
			
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
			try {
				// The player sleeps for 500ms after his turn
				while(!isMyTurn()) {
					Thread.sleep(500);
				}
			}
			
			catch(InterruptedException e) {}
		}
	}
	
}
