package XO;

public class SelfGame extends Game {
	private SelfPlayer playerX;
    private SelfPlayer playerO;

    public SelfGame() {
        super();
        playerX = new SelfPlayer('X', this);
        playerO = new SelfPlayer('O', this);
    }

    @Override
    public char getTurn() {
        return playerX.isMyTurn() ? 'X' : 'O';
    }


}
