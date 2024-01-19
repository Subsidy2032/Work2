package XO;

/**
 * This is an abstract class to handle a player
 * @author Ron Bitan (315924316) && Noam Muchink (212472484)
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
	 * To track if the player should keep playing
	 */
	private boolean keepPlaying;
	/**
	 * An object that is used as a lock
	 */
	protected static final Object LOCK = new Object();
	
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
	 * 
	 * @return If the player should keep playing
	 */
	public boolean getKeepPlaying() {
		return keepPlaying;
	}
	
	/**
	 * 
	 * @param keepPlaying To set if the player should keep playing
	 */
	public void setKeepPlaying(boolean keepPlaying) {
		this.keepPlaying = keepPlaying;
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
