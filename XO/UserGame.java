package XO;

public class UserGame extends Game {
	private SelfPlayer computer;
    private UserPlayer user;

    public UserGame(UserPlayer user) {
        super();
        this.user = user;
        this.computer = new SelfPlayer(user.playerType == 'X' ? 'O':'X' ,this);
        this.setTurn(user.playerType == 'X' ? computer:user);
    }
    
    public void startGame() {
    	
    }
}
