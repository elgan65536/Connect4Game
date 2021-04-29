package pkgCore;

import pkgException.ColumnFullException;

public class Board {
	private Piece[][] pieces;
	private final int height;
	private final int width;
	private final int nToWin;
	
	public Board() {
		this(6,7);
	}
	
	public Board(int rows, int columns) {
		this(rows,columns,4);
	}
	
	public Board(int rows, int columns, int n) {
		pieces = new Piece[rows][columns];
		height = rows;
		width = columns;
		nToWin = n;
	}
	
	public Piece getPiece(int row, int column) {
		return pieces[row][column];
	}
	
	public void insertPiece(int column, char color) throws ColumnFullException {
		if (columnIsFull(column)) {
			throw new ColumnFullException(column);
		}
		int lowest = 0;
		for (int row = 0; row < height; row++) {
			if (pieces[row][column] == null) {
				lowest = row;
			}
		}
		pieces[lowest][column] = new Piece(color);
	}
	
	public boolean columnIsFull(int column) {
		return pieces[0][column] != null;
	}
	
	public boolean isFull() {
		for (int i = 0; i < width; i++) {
			if (!columnIsFull(i)) {
				return false;
			}
		}
		return true;
	}
	
	public char isWinning() {
		//vertical
		for (int i = 0; i < height - nToWin + 1; i++) {
			for (int j = 0; j < width; j++) {
				if (pieces[i][j] != null) {
					boolean winPossible = true;
					for (int k = 1; k < nToWin; k++) {
						if (!pieces[i][j].equalTo(pieces[i+k][j])) {
							winPossible = false;
						}
					}
					if (winPossible) {
						return pieces[i][j].getColor();
					}
				}
			}
		}
		//horizontal
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width - nToWin + 1; j++) {
				if (pieces[i][j] != null) {
					boolean winPossible = true;
					for (int k = 1; k < nToWin; k++) {
						if (!pieces[i][j].equalTo(pieces[i][j+k])) {
							winPossible = false;
						}
					}
					if (winPossible) {
						return pieces[i][j].getColor();
					}
				}
			}
		}
		//diagonal up-left
		for (int i = 0; i < height - nToWin + 1; i++) {
			for (int j = 0; j < width - nToWin + 1; j++) {
				if (pieces[i][j] != null) {
					boolean winPossible = true;
					for (int k = 1; k < nToWin; k++) {
						if (!pieces[i][j].equalTo(pieces[i+k][j+k])) {
							winPossible = false;
						}
					}
					if (winPossible) {
						return pieces[i][j].getColor();
					}
				}
			}
		}
		//diagonal up-right
		for (int i = 0; i < height - nToWin + 1; i++) {
			for (int j = 0; j < width - nToWin + 1; j++) {
				if (pieces[i][j+nToWin-1] != null) {
					boolean winPossible = true;
					for (int k = 1; k < nToWin; k++) {
						if (!pieces[i][j+nToWin-1].equalTo(pieces[i+k][j+nToWin-1-k])) {
							winPossible = false;
						}
					}
					if (winPossible) {
						return pieces[i][j+nToWin-1].getColor();
					}
				}
			}
		}
		return 0;
	}
	
	public void printBoard() {
		for (Piece[] i : pieces) {
			for (Piece j : i) {
				System.out.print("[" + ((j == null) ? " " : j.getColor()) + "]");
			}
			System.out.print("\n");
		}
	}
}