package XO;

/**
 * This is a class for a user game (user vs computer)
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
    	Thread computerThread = new Thread(computer);
    	Thread userThread = new Thread(user);
    	
    	computerThread.start();
    	userThread.start();
    	
    	try {
    		computerThread.join();
    		userThread.join();
    	}
    	
    	catch(InterruptedException e) {}
    	
    	// Prints who won the game, if someone won
    	if(isWinner(computer) || isWinner(user)) {
    		printBoard();
    		if(isWinner(computer.playerType == 'X' ? computer:user)) {
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
