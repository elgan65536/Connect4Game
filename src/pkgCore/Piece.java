package pkgCore;

public class Piece {
	private final char color;
	
	public Piece(char c) {
		color = c;
	}
	
	public char getColor() {
		return color;
	}
	
	public boolean equalTo(Piece...pieces) {
		for (Piece piece : pieces) {
			if (piece == null) {
				return false;
			}
			if (piece.getColor() != color) {
				return false;
			}
		}
	return true;
	}
}