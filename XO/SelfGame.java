package XO;

/**
 * This is a class for self game (the computer vs itself)
 *
 */
public class SelfGame extends Game {
	private SelfPlayer playerX;
    private SelfPlayer playerO;
    
    /**
     * Constructor to create a game with 2 self players and to set the turn to X
     */
    public SelfGame() {
    	super();
        playerX = new SelfPlayer('X', this);
        playerO = new SelfPlayer('O', this);
        this.setTurn(playerO);
    }
    
    /**
     * A method to start the game
     */
    public void startGame() {
    	Thread threadX = new Thread(playerX);
    	Thread threadY = new Thread(playerO);
    	
    	threadX.start();
    	threadY.start();
    	
    	// Making sure only the 2 created threads are running
    	try {
	    	threadX.join();
	    	threadY.join();
    	}
    	
    	catch(InterruptedException e) {}
    	
    	// Prints who won the game, if someone won
    	if(isWinner(playerX) || isWinner(playerO)) {
    		printBoard();
    		if(isWinner(playerX)) {
    			System.out.println("Player X has won!");
    		}
    		
    		else
    			System.out.println("Player O has won!");
    		
    	}
    	
    	// Prints that the board is full if no one won
    	else if(this.isBoardFull()) {
    		System.out.println("Board is full");
    	}
    }
}
