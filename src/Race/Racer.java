package Race;

public class Racer implements Runnable {
	private static int globalId = 1;
	private int id;
	private int speed;
	private Track track;
	
	public Racer(Integer speed, Track track) throws Exception {
		if(speed < 0 || speed > 10)
			throw new Exception("Speed must be between 1-10");
		
		if(!(speed instanceof Integer)) {
			throw new Exception("Speed must be integer");
		}
		
		this.speed = speed;
		this.track = track;
		id = globalId;
		globalId++;
	}
	
	public void go() {
		Thread.currentThread().setPriority(speed);
		
		for(int i = 1; i <= 100; i++) {
			System.out.println("Runner " + id + " ran " + i + " meters.");
		}
		
		track.finishedRacers++;
		System.out.println("Runner " + id + " finished " + track.finishedRacers + ((track.finishedRacers == 1) ? "st":(track.finishedRacers == 2) ? "nd":(track.finishedRacers == 3) ? "rd" : "th"));
	}
	
	public void run() {
		go();
	}
}
