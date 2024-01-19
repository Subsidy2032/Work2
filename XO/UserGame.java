package XO;

/**
 * This is a class for a user game (user vs computer)
 * @author Ron Bitan (315924316) && Noam Muchink (212472484)
 *
 */
public class UserGame extends Game {
	/**
	 * The computer player
	 */
	private SelfPlayer computer;
	/**
	 * The user player
	 */
    private UserPlayer user;
    
    /**
     * A constructor that creates a user game and sets the starting player to type X
     * @param toPlay The type the user wants to play
     */
    public UserGame(char toPlay) {
        super();
        this.user = new UserPlayer(toPlay, this);
        this.computer = new SelfPlayer(toPlay == 'X' ? 'O':'X' ,this);
        this.setTurn(user.playerType == 'X' ? computer:user);
    }
    
    /**
     * A method to start the user game
     */
    public void startGame() {
    	Thread t1 = new Thread(computer);
    	Thread t2 = new Thread(user);
    	
    	t1.start();
    	t2.start();
    	while(true) {
    		// Stop the players if one of them won and print the board and the winner
	    	if(isWinner(computer) || isWinner(user)) {
	    		t1.interrupt();
	    		t2.interrupt();
	    		
	    		printBoard();
	    		if(isWinner(computer.playerType == 'X' ? computer:user)) {
	    			System.out.println("Player X has won!");
	    		}
	    		
	    		else
	    			System.out.println("Player O has won!");
	    		
	    		break;
	    	}
    	}
    }
}
