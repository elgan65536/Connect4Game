package pkgCore;

import pkgException.ColumnFullException;

public class Board {
	private Piece[][] pieces;
	private int height;
	private int width;
	
	public Board() {
		this(6,7);
	}
	
	public Board(int rows, int columns) {
		pieces = new Piece[rows][columns];
		height = rows;
		width = columns;
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
		for (Piece[] i : pieces) {
			for (Piece j : i) {
				if (j == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	public char isWinning() {
		char winning = 0;
		//vert
		for (int i = 0; i < height - 3; i++) {
			for (int j = 0; j < width; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].equals(pieces[i+1][j],pieces[i+2][j],pieces[i+3][j])) {
						winning = pieces[i][j].getColor();
					}
				}
			}
		}
		//horiz
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width - 3; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].equals(pieces[i][j+1],pieces[i][j+2],pieces[i][j+3])) {
						winning = pieces[i][j].getColor();
					}
				}
			}
		}
		//diag upright
		for (int i = 0; i < height - 3; i++) {
			for (int j = 0; j < width - 3; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].equals(pieces[i+1][j+1],pieces[i+2][j+2],pieces[i+3][j+3])) {
						winning = pieces[i][j].getColor();
					}
				}
			}
		}
		//diag upleft
		for (int i = 0; i < height - 3; i++) {
			for (int j = 0; j < width - 3; j++) {
				if (pieces[i][j+3] != null) {
					if (pieces[i][j+3].equals(pieces[i+1][j+2],pieces[i+2][j+1],pieces[i+3][j])) {
						winning = pieces[i][j+3].getColor();
					}
				}
			}
		}
		return winning;
	}
	
	public void printBoard() {
		for (Piece[] i : pieces) {
			for (Piece j : i) {
				if (j == null) {
					System.out.print("[ ]");
				} else {
					System.out.print("[" + j.getColor() + "]");
				}
			}
			System.out.print("\n");
		}
	}
}
