package XO;

/**
 * This is a class for self game (the computer vs itself)
 * @author Ron Bitan (315924316) && Noam Muchink (212472484)
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
    	Thread t1 = new Thread(playerX);
    	Thread t2 = new Thread(playerO);
    	
    	t1.start();
    	t2.start();
    	while(true) {
    		// Stop the players if one of them won and print the board and the winner
	    	if(isWinner(playerX) || isWinner(playerO)) {
	    		t1.interrupt();
	    		t2.interrupt();
	    		
	    		printBoard();
	    		if(isWinner(playerX)) {
	    			System.out.println("The X player won");
	    		}
	    		
	    		else
	    			System.out.println("The O player won");
	    		
	    		break;
	    	}
    	}
    }
}
