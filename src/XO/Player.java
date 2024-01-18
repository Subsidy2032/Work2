package XO;
import XO.Game;

public abstract class Player extends Thread {
	protected char playerType;
	protected Game game;

	public Player(char playerType, Game game) {
		this.playerType = playerType;
		this.game = game;
	}
	public char getPlayerType() {
		return playerType;
	}

	public void setPlayerType(char playerType) {
		this.playerType = playerType;
	}


	public abstract boolean isMyTurn();

	public abstract void playTurn();
	//public abstract GameCoordinates coordToPlay(GameCoordinates[] coordinates);
}
