package Race;
/**
 * A class to represent a racer
 * @author Ron Bitan (315924316) && Noam Muchink (212472484)
 *
 */
public class Racer implements Runnable {
	/**
	 * To track the number of joined racers
	 */
	private static int globalId = 1;
	/**
	 * To save the ID of the racer
	 */
	private int id;
	/**
	 * The speed of the racer
	 */
	private int speed;
	/**
	 * The track the racer is in
	 */
	private Track track;
	/**
	 * An object that is used as a lock
	 */
	private static final Object LOCK = new Object();
	
	/**
	 * The constructor to build the racer
	 * @param speed The speed of the player
	 * @param track the track of the player
	 * @throws Exception if the speed is invalid
	 */
	public Racer(int speed, Track track) throws Exception {
		if(speed < 0 || speed > 10)
			throw new IllegalArgumentException("Speed must be between 1-10");
		
		this.speed = speed;
		this.track = track;
		id = globalId;
		globalId++;
	}
	
	/**
	 * For the player to start running
	 */
	public void go() {
		Thread.currentThread().setPriority(speed); // Sets the speed of the player
		
		// A loop of the player running
		for(int i = 1; i <= 100; i++) {
			System.out.println("Runner " + id + " ran " + i + " meters.");
		}
		
		// Prints the finishing place
		synchronized(LOCK) {
			track.finishedRacers++;
			System.out.println("Runner " + id + " finished " + track.finishedRacers + ((track.finishedRacers == 1) ? "st":(track.finishedRacers == 2) ? "nd":(track.finishedRacers == 3) ? "rd" : "th"));
		}
	}
	
	/**
	 * Starts the thread
	 */
	public void run() {
		go();
	}
}
