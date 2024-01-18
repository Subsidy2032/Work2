package XO;

import java.util.List;
import java.util.ArrayList;

/**
 * This is an abstract class to handle a game
 * @author Ron Bitan (315924316) && Noam Muchink (212472484)
 *
 */
public abstract class Game {
	/**
	 * The game board
	 */
	protected char[][] gameBoard = new char[5][5];
	/**
	 * The current player type's turn
	 */
	private char turn;
	
	/**
	 * 
	 * @return The type of the current player's turn
	 */
	public char getTurn() {
		return turn;
	}
	
	/**
	 * 
	 * @param played The player that's just played
	 */
	public void setTurn(Player played) {
		turn = played.getPlayerType() == 'X' ? 'O':'X';
	}
	
	/**
	 * Checks if a cell is free, by row and column
	 * @param row The row to check
	 * @param col The column to check
	 * @return True if cell is empty, false otherwise
	 */
	public boolean isCellFree(int row, int col) {
		return gameBoard[row][col] == '\u0000';
	}
	
	/**
	 * Prints the entire board
	 */
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
	
	/**
	 * Returns a list of all the free cells in the board
	 * @return List of free cells
	 */
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
	
	/**
	 * Checks if the board is full
	 * @return True or false
	 */
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
	
	/**
	 * Checks if a player is won
	 * Uses {@link winningByRowCol(Player p) winningByRowCol} and {@link winningByDiagonal(Player p) winningByDiagonal}
	 * @param p The player to check
	 * @return True or false
	 */
	public boolean isWinner(Player p) {
		return (winningByRowCol(p) || winningByDiagonal(p));
	}
	
	/**
	 * Auxiliary function to check if the player won by row or column
	 * @param p The player to check
	 * @return True or false
	 */
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
	
	/**
	 * Auxiliary function to check if the player won by one of the diagonals
	 * @param p The player to check
	 * @return True or false
	 */
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
	
	/**
	 * Gets a coordinate and char of X or O and puts the char in the coordinate
	 * @param coord The coordinate
	 * @param XorO The char to put
	 */
	public void placeXOinBoard(GameCoordinates coord, char XorO) {
		gameBoard[coord.getRow()][coord.getCol()] = XorO;
	}
	
	public abstract void startGame();
}