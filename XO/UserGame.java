package XO;

public class UserGame extends Game {
	private SelfPlayer computer;
    private UserPlayer user;

    public UserGame(char toPlay) {
        super();
        this.user = new UserPlayer(toPlay, this);
        this.computer = new SelfPlayer(toPlay == 'X' ? 'O':'X' ,this);
        this.setTurn(user.playerType == 'X' ? computer:user);
    }
    
    public void startGame() {
    	Thread t1 = new Thread(computer);
    	Thread t2 = new Thread(user);
    	
    	t1.start();
    	t2.start();
    	while(true) {
	    	if(isWinner(computer) || isWinner(user)) {
	    		t1.interrupt();
	    		t2.interrupt();
	    		
	    		printBoard();
	    		if(isWinner(computer.playerType == 'X' ? computer:user)) {
	    			System.out.println("The X player won");
	    		}
	    		
	    		else
	    			System.out.println("The O player won");
	    		
	    		break;
	    	}
    	}
    }
}
