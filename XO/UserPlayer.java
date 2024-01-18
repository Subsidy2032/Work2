package XO;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class UserPlayer extends Player implements Runnable {
	Scanner scanner = new Scanner(System.in);
	
	public UserPlayer(char playerType, Game game) {
		super(playerType, game);
	}
	
	public GameCoordinates coordToPlay(List<GameCoordinates> freeCells) {
		System.out.println("Enter row and column (separated by space): ");
		Scanner scanner = new Scanner(System.in);
		int row = -1;
		int col = -1;

		while (true) {
			try {
				row = scanner.nextInt();
				col = scanner.nextInt();
				
				if (row > 0 && row <= 5 && col > 0 && col <= 5 && game.isCellFree(row - 1, col - 1)) {
					break;
				} else {
					System.out.println("Invalid input. Try again.");
				}
			}
			
			catch (Exception e) {
				System.out.println("Invalid input. Try again.");
				scanner.nextLine();
			}
		}

		GameCoordinates playerChoice = new GameCoordinates(row - 1, col - 1);
		return playerChoice;
	}
	
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
		GameCoordinates playerChoice = coordToPlay(freeCells);
		game.placeXOinBoard(playerChoice, playerType);
		
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
