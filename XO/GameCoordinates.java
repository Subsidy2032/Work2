package XO;

/**
 * A class to describe a coordinate on the game board
 *
 */
public class GameCoordinates {
	/**
	 * The row in the board
	 */
	private int row;
	/**
	 * The column in the board
	 */
	private int col;
	
	/**
	 * A constructor to build a coordinate
	 * @param row The row in the board
	 * @param col The column in the board
	 */
	public GameCoordinates(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
}
