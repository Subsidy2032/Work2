package XO;
import java.util.List;

import XO.Game;

public abstract class Player {
	protected char playerType;
	protected Game game;
	private boolean keepPlaying;

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
	
	public boolean getKeepPlaying() {
		return keepPlaying;
	}
	
	public void setKeepPlaying(boolean keepPlaying) {
		this.keepPlaying = keepPlaying;
	}
	
	public boolean isMyTurn() {
		return game.getTurn() == playerType;
	}
	
	public abstract GameCoordinates coordToPlay(List<GameCoordinates> freeCells);
	public abstract void playTurn();
}
