package XO;
import java.util.List;
import java.util.Random;

/**
 * A class for self player (computer)
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
		synchronized(game) {
			try {
				// The player waits for his turn
				while(!isMyTurn()) {
					game.wait();
				}
			}
			
			catch(InterruptedException e) {}
			
			// Exists the function if the board is full
			if(game.isBoardFull() || !game.getKeepPlaying()) {
				game.setKeepPlaying(false);
				return;
			}
			
			// Places the player type char's on a random cell in the board and sets the turn to the other player
			List<GameCoordinates> freeCells = game.getFreeCells();
			GameCoordinates randomCoordinate = getRandomCell(freeCells);
			game.placeXOinBoard(randomCoordinate, playerType);
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
	}
	
}
