package XO;

public class SelfGame extends Game {
	private SelfPlayer playerX;
    private SelfPlayer playerO;

    public SelfGame() {
    	super();
        playerX = new SelfPlayer('X', this);
        playerO = new SelfPlayer('O', this);
        this.setTurn(playerO);
    }
    
    public void startGame() {
    	Thread t1 = new Thread(playerX);
    	Thread t2 = new Thread(playerO);
    	
    	t1.start();
    	t2.start();
    	while(true) {
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
