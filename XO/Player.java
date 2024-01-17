package XO;
import XO.Game;

public abstract class Player {
	protected char playerType;

	public Player(char type) {
		playerType = type;
	}
	
	public char getPlayerType() {
		return playerType;
	}

	public void setPlayerType(char playerType) {
		this.playerType = playerType;
	}
	
	public abstract GameCoordinates coordToPlay(GameCoordinates[] coordinates);
}
