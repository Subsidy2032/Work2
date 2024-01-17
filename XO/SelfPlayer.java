package XO;
import java.util.Random;

public class SelfPlayer extends Player implements Runnable {
	public SelfPlayer(char type) {
		super(type);
	}
	
	public GameCoordinates getRandomCell(GameCoordinates[] coordinates) {
		Random generator = new Random();
		int randomIndex = generator.nextInt(coordinates.length);
		return coordinates[randomIndex];
	}
	
	public GameCoordinates coordToPlay(GameCoordinates[] coordinates) {
		GameCoordinates toPlay = getRandomCell(coordinates);
		return toPlay;
	}
	
	public void run() {
		
	}
	
}
