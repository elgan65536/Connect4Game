package pkgCore;

import java.util.Scanner;

import pkgException.ColumnFullException;

public class Connect4Game {

	public static void main(String[] args) {
		Board gameBoard = new Board();
		char winner = 0;
		char currentPlayer = 'X';
		boolean gameIsRunning = true;
		while (gameIsRunning) {
			newMove(currentPlayer,gameBoard);
			winner = gameBoard.isWinning();
			if (winner != 0) {
				gameIsRunning = false;
			}
			if (gameBoard.isFull()) {
				gameIsRunning = false;
			}
			currentPlayer = swap(currentPlayer);
		}
		gameBoard.printBoard();
		if (winner == 0) {
			System.out.print("The game is a draw");
		} else {
			System.out.print(winner + " wins");
		}
	}
	
	public static void newMove(char player, Board b) {
		Scanner sc = new Scanner(System.in);
		boolean moveSuccessful = false;
		while (!moveSuccessful) {
			moveSuccessful = true;
			System.out.println(player + " to move:");
			b.printBoard();
			int column = sc.nextInt();
			try {
				b.insertPiece(column, player);
			}
			catch (ColumnFullException e) {
				System.out.println("column " + e.getColumn() + " is full");
				moveSuccessful = false;
			}
			catch (Exception e) {
				System.out.println("Invalid move");
				moveSuccessful = false;
			}
		}
	}
	
	public static char swap(char c) {
		if (c == 'X') {
			c = 'O';
		} else if (c == 'O') {
			c = 'X';
		}
		return c;
	}
}
