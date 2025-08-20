package XO;

/**
 * This is an abstract class to handle a player
 *
 */
public abstract class Player {
	/**
	 * The type of the player
	 */
	protected char playerType;
	/**
	 * The game the player is part of
	 */
	protected Game game;
	
	/**
	 * Constructor to create a player associated with a game
	 * @param playerType The type of the player
	 * @param game The game the player is part of
	 */
	public Player(char playerType, Game game) {
		this.playerType = playerType;
		this.game = game;
	}
	
	/**
	 * 
	 * @return The type of the player
	 */
	public char getPlayerType() {
		return playerType;
	}
	
	/**
	 * A method to check if it's the turn of a player
	 * @return True or false
	 */
	public boolean isMyTurn() {
		return game.getTurn() == playerType;
	}
	
	public abstract void playTurn();
}
