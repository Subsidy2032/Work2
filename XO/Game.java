package XO;

import java.util.Arrays;

public abstract class Game {
	private char[][] gameBoard = new char[5][5];
	private Player p;
	private GameCoordinates coord;
	
	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public GameCoordinates getCoord() {
		return coord;
	}

	public void setCoord(GameCoordinates coord) {
		this.coord = coord;
	}
	
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
	
	public Player getTurn() {
		return p;
	}
	
	public GameCoordinates[] getFreeCells() {
		int counter = 0;
		GameCoordinates[] coords = new GameCoordinates[25];
		for(int i = 0; i < gameBoard.length; i++) {
			for(int j = 0; j < gameBoard[i].length; j++)
				if(gameBoard[i][j] == '\u0000') {
					coords[counter] = new GameCoordinates(i, j);
					counter++;
				}
		}
		
		coords = Arrays.copyOfRange(coords, 0, counter);
		return coords;
	}
	
	public boolean isWinner(Player p) {
		
	}
	
	private boolean rowORcolWinner(Player p) {
		for(int i = 0; i < gameBoard.length; i++) {
			if(gameBoard[i][0] == p.playerType && 
				gameBoard[i][1] == p.playerType && 
				gameBoard[i][2] == p.playerType && 
				gameBoard[i][3] == p.playerType)
				return true;
			
			if(gameBoard[i][1] == p.playerType && 
				gameBoard[i][2] == p.playerType && 
				gameBoard[i][3] == p.playerType && 
				gameBoard[i][4] == p.playerType)
				return true;
			
			if(gameBoard[0][i] == p.playerType && 
				gameBoard[1][i] == p.playerType && 
				gameBoard[2][i] == p.playerType && 
				gameBoard[3][i] == p.playerType)
				return true;
			
			if(gameBoard[1][i] == p.playerType && 
				gameBoard[2][i] == p.playerType && 
				gameBoard[3][i] == p.playerType && 
				gameBoard[4][i] == p.playerType) 
				return true;
		}
		
		return false;
	}
	
	private boolean diagonalWinner(Player p) {
		for(int i = 0; i < 2; i++) {
			if(gameBoard[i][i] == p.playerType && 
				gameBoard[i + 1][i + 1] == p.playerType && 
				gameBoard[i + 2][i + 2] == p.playerType && 
				gameBoard[i + 3][i + 3] == p.playerType)
				return true;
			
			if(gameBoard[1 - i][i + 3] == p.playerType && 
				gameBoard[2 - i][i + 2] == p.playerType && 
				gameBoard[3 - i][i + 1] == p.playerType && 
				gameBoard[4 - i][i] == p.playerType)
				return true;
		}
		
		boolean exists1 = true;
		boolean exists2 = true;
		boolean exists3 = true;
		boolean exists4 = true;
		
		for(int i = 0; i < 4; i++) {
			if(gameBoard[i][i + 1] != p.playerType)
				exists1 = false;
			
			if(gameBoard[i + 1][i] != p.playerType)
				exists2 = false;
			
			if(gameBoard[i][3 - i] != p.playerType)
				exists3 = false;
			
			if(gameBoard[3 - i][i] != p.playerType)
				exists4 = false;
		}
	}
	
	/*
	public boolean rowORcolWinner(Player p) {
		int rowCount;
		int colCount;
		
		for(int i = 0; i < gameBoard.length; i++) {
			rowCount = 0;
			colCount = 0;
			for(int j = 0; j < gameBoard[i].length; j++) {
				if(gameBoard[i][j] != p.playerType) {
					if(j == 0 || j == 4)
						rowCount++;
					else
						rowCount += 2;
				}
				
				if(gameBoard[j][i] != p.playerType) {
					if(i == 0 || i == 4)
						colCount++;
					else
						colCount += 2;
				}
			}
			
			if(colCount < 2 || rowCount < 2)
				return true;
		}
		
		return false;
	}
	*/
}