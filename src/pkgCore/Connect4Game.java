package pkgCore;

import java.util.Scanner;
import pkgException.ColumnFullException;

public class Connect4Game {
	
	private static Board gameBoard = new Board();
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] arguments) {
		char winner = 0;
		char currentPlayer = 'X';
		boolean gameIsRunning = true;
		while (gameIsRunning) {
			newMove(currentPlayer);
			winner = gameBoard.isWinning();
			if (winner != 0 || gameBoard.isFull()) {
				gameIsRunning = false;
			}
			currentPlayer = swap(currentPlayer);
		}
		gameBoard.printBoard();
		System.out.print((winner == 0) ? "The game is a draw" : winner + " wins");
	}
	
	public static void newMove(char player) {
		boolean moveSuccessful = false;
		while (!moveSuccessful) {
			moveSuccessful = true;
			System.out.println(player + " to move:");
			gameBoard.printBoard();
			int column = sc.nextInt();
			try {
				gameBoard.insertPiece(column, player);
			} catch (ColumnFullException e) {
				System.out.println("column " + e.getColumn() + " is full");
				moveSuccessful = false;
			} catch (Exception e) {
				System.out.println("Invalid move");
				moveSuccessful = false;
			}
		}
	}
	
	public static char swap(char c) {
		switch (c) {
		case 'X':
			return 'O';
		case 'O':
			return 'X';
		default:
			return 0;
		}
	}
}