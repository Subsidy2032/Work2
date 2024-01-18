package XO;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public abstract class Game {
	protected char[][] gameBoard = new char[5][5];
	private char turn;
	
	public char getTurn() {
		return turn;
	}
	
	public void setTurn(Player played) {
		turn = played.getPlayerType() == 'X' ? 'O':'X';
	}
	
	public boolean isCellFree(int row, int col) {
		return gameBoard[row][col] == '\u0000';
	}

	public void printBoard() {
		for (int i = 0; i <= 4; i++) {
			for (int j = 0; j <= 4; j++) {
				if (gameBoard[i][j] == 'X' || gameBoard[i][j] == 'O')
					System.out.print(gameBoard[i][j]);
				else
					System.out.print("-");

				if (j < 4)
					System.out.print(" ");
			}
			System.out.println();
		}
		
		System.out.println();
	}

	public List<GameCoordinates> getFreeCells() {
		List<GameCoordinates> freeCells = new ArrayList<>();
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				if (gameBoard[i][j] == '\u0000') {
					freeCells.add(new GameCoordinates(i, j));
				}
			}
		}
		return freeCells;
	}
	
	public boolean isBoardFull() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (gameBoard[i][j] == '\u0000') {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isWinner(Player p) {
		return (winningByRowCol(p) || winningByDiagonal(p));
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
	
	public void placeXOinBoard(GameCoordinates coord, char XorO) {
		gameBoard[coord.getRow()][coord.getCol()] = XorO;
	}
	
	public abstract void startGame();
}