package XO;

public class UserGame extends Game {
	private UserPlayer up;
	private SelfPlayer sp;
	
	public UserGame(UserPlayer up, SelfPlayer sp) {
		super(up, sp);
	}
}
