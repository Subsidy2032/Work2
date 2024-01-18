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
	}
	
	/**
	 * Runs the thread
	 */
	public void run() {
		setKeepPlaying(true);
		
		while(getKeepPlaying()) {
			playTurn();
		}
	}
	
}
