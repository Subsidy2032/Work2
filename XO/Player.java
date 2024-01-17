package XO;
import XO.Game;

public abstract class Player {
	protected char playerType;
	protected Game sg;

	public Player(char playerType, Game sg) {
		this.playerType = playerType;
		this.sg = sg;
	}
	
	public char getPlayerType() {
		return playerType;
	}

	public void setPlayerType(char playerType) {
		this.playerType = playerType;
	}
	
	public abstract GameCoordinates coordToPlay(GameCoordinates[] coordinates);
	public abstract void play();
}
