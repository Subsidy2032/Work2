package Race;

public class Main {

	public static void main(String[] args) {
		Track track = new Track();
		Racer racer1 = null;
		Racer racer2 = null;
		Racer racer3 = null;
		Racer racer4 = null;
		
		// Initializes the racers
		try {
			racer1 = new Racer(5, track); //10
			racer2 = new Racer(5, track); //2
			racer3 = new Racer(5, track); //3
			racer4 = new Racer(5, track); //7
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
