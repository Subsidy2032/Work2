package XO;

import java.util.Arrays;

public abstract class Game {
	private char[][] gameBoard = new char[5][5];
	private Player p1;
	private Player p2;
	private Player turn;
	private GameCoordinates coord;
	
	public Game(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public char[][] getGameBoard() {
		return gameBoard;
	}
	

	public GameCoordinates getCoord() {
		return coord;
	}

	public void setCoord(GameCoordinates coord) {
		this.coord = coord;
	}
	
	public Player getTurn() {
		return turn;
	}
	
	public void setTurn(Player turn) {
		this.turn = turn;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public void printBoard() {
		for (int i = 0; i <= 4; i++) {
			for (int j = 0; j <= 4; j++) {
				if (gameBoard[i][j] == 'X' || gameBoard[i][j] == 'O')
					System.out.print(gameBoard[i][j]);
				else
					System.out.print(" ");

				if (j < 4)
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	public GameCoordinates[] getFreeCells() {
		int counter = 0;
		GameCoordinates[] coords = new GameCoordinates[25];
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++)
				if (gameBoard[i][j] == '\u0000') {
					coords[counter] = new GameCoordinates(i, j);
					counter++;
				}
		}

		coords = Arrays.copyOfRange(coords, 0, counter);
		return coords;
	}
	
	public boolean isWinner(Player p) {
		return (winningByRowCol(p) || winningByDiagonal(p));
		// System.out.print(winner) stop game
	}

	private boolean winningByRowCol(Player p) {
		for (int i = 0; i < gameBoard.length; i++) {
			if (gameBoard[i][0] == p.playerType &&
					gameBoard[i][1] == p.playerType &&
					gameBoard[i][2] == p.playerType &&
					gameBoard[i][3] == p.playerType)
				return true;

			if (gameBoard[i][1] == p.playerType &&
					gameBoard[i][2] == p.playerType &&
					gameBoard[i][3] == p.playerType &&
					gameBoard[i][4] == p.playerType)
				return true;

			if (gameBoard[0][i] == p.playerType &&
					gameBoard[1][i] == p.playerType &&
					gameBoard[2][i] == p.playerType &&
					gameBoard[3][i] == p.playerType)
				return true;

			if (gameBoard[1][i] == p.playerType &&
					gameBoard[2][i] == p.playerType &&
					gameBoard[3][i] == p.playerType &&
					gameBoard[4][i] == p.playerType)
				return true;
		}

		return false;
	}

	private boolean winningByDiagonal(Player p) {
		// By Bill Gates
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (gameBoard[i][j] == p.playerType &&
						gameBoard[i + 1][j + 1] == p.playerType &&
						gameBoard[i + 2][j + 2] == p.playerType &&
						gameBoard[i + 3][j + 3] == p.playerType)
					return true;

				if (gameBoard[i][j + 3] == p.playerType &&
						gameBoard[i + 1][j + 2] == p.playerType &&
						gameBoard[i + 2][j + 1] == p.playerType &&
						gameBoard[i + 3][j] == p.playerType)
					return true;
			}
		}
		return false;
	}
}