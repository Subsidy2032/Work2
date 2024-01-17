package XO;
import java.util.Scanner;

public class UserPlayer extends Player implements Runnable {
	Scanner scan = new Scanner(System.in);
	
	public UserPlayer(char playerType, UserGame sg) {
		super(playerType, sg);
	}
	
	public GameCoordinates coordToPlay(GameCoordinates[] coordinates) {
		
		return null;
	}
	
	public synchronized void play() {
		
	}
	
	public void run() {
	}
}
