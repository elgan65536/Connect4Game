package pkgException;

public class ColumnFullException extends Exception {
	private int column;
	public ColumnFullException(int c) {
		column = c;
	}
	
	public int getColumn() {
		return column;
	}
}