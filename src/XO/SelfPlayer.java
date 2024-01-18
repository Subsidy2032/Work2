package XO;
import java.util.Random;
import java.util.List;

public class SelfPlayer extends Player {
	
	public SelfPlayer(char playerType, Game game) {
		super(playerType, game);
	}

	@Override
	public boolean isMyTurn() {
		return game.getTurn() == playerType;
	}

	@Override
	public synchronized void playTurn(){
		if (!isMyTurn()) {
			return;
		}

		List<GameCoordinates> freeCells = game.getFreeCells();
		if (!freeCells.isEmpty()) {
			GameCoordinates randomCoordinate = getRandomCell(freeCells);
			game.placeXOinBoard(randomCoordinate, playerType);
		}
	}

	public GameCoordinates getRandomCell(List<GameCoordinates> coordinates) {
		Random generator = new Random();
		int randomIndex = generator.nextInt(coordinates.size());

		return coordinates.get(randomIndex);
	}
	
	/*public GameCoordinates coordToPlay(GameCoordinates[] coordinates) {
		GameCoordinates toPlay = getRandomCell(coordinates);
		return toPlay;
	}
	*/

	public void run() {

	}
	
}
