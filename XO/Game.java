package XO;

public abstract class Game {
	private char[][] gameBoard = new char[5][5];
	private Player p;
	
	public void printBoard() {
		for(int i = 0; i <= 4; i++) {
			for(int j = 0; j <= 4; j++) {
				if(gameBoard[i][j] == 'X' || gameBoard[i][j] == 'O')
					System.out.print(gameBoard[i][j]);
				else
					System.out.print(" ");
				
				if(j < 4)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public char getTurn() {
		return p.playerType;
	}
}
