package XO;
import java.util.Random;

public class SelfPlayer extends Player implements Runnable {
	
	public SelfPlayer(char playerType, Game sg) {
		super(playerType, sg);
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
	
	public synchronized void play() {
		try {
			while(playerType != sg.getTurn().playerType)
				Thread.sleep(500);
		}
		
		catch(Exception e) {}
	}
	
	public void run() {
		boolean keepPlaying = true;
		
		while(keepPlaying) {
		}
	}
	
}
