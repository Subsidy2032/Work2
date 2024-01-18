package Race;

// Ron Bitan (315924316) && Noam Muchink (212472484)
// Github: https://github.com/Subsidy2032/Work2
public class Main {

	public static void main(String[] args) {
		Track track = new Track();
		Racer racer1 = null;
		Racer racer2 = null;
		Racer racer3 = null;
		Racer racer4 = null;
		
		// Initializes the racers
		try {
			racer1 = new Racer(10, track);
			racer2 = new Racer(2, track);
			racer3 = new Racer(3, track);
			racer4 = new Racer(7, track);
		}
		
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// Initializes the threats and starts them
		Thread t1 = new Thread(racer1);
		Thread t2 = new Thread(racer2);
		Thread t3 = new Thread(racer3);
		Thread t4 = new Thread(racer4);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
