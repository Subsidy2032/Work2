package XO;
import java.util.List;
import java.util.Random;

public class SelfPlayer extends Player implements Runnable {
	
	public SelfPlayer(char playerType, Game game) {
		super(playerType, game);
	}
	
	public GameCoordinates getRandomCell(List<GameCoordinates> freeCells) {
		Random generator = new Random();
		int randomIndex = generator.nextInt(freeCells.size());
		return freeCells.get(randomIndex);
	}
	
	public GameCoordinates coordToPlay(List<GameCoordinates> freeCells) {
		GameCoordinates toPlay = getRandomCell(freeCells);
		return toPlay;
	}
	
	@Override
	public synchronized void playTurn() {
		try {
			while(!isMyTurn()) {
				Thread.sleep(500);
				if(game.isBoardFull()) {
					if(playerType == 'X')
						System.out.println("Board is full");
					setKeepPlaying(false);
					return;
				}
				
			}
		}
		
		catch(InterruptedException e) {}
		
		if(game.isWinner(playerType == 'X' ? new SelfPlayer('O', game):new SelfPlayer('X', game))) {
			setKeepPlaying(false);
			return;
		}
		List<GameCoordinates> freeCells = game.getFreeCells();
		GameCoordinates randomCoordinate = getRandomCell(freeCells);
		game.placeXOinBoard(randomCoordinate, playerType);
		
		if(game.isWinner(this)) {
			setKeepPlaying(false);
			return;
		}
		
		game.printBoard();
		game.setTurn(this);
	}
	
	public void run() {
		setKeepPlaying(true);
		
		while(getKeepPlaying()) {
			playTurn();
		}
	}
	
}
