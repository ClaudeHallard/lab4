package lab4.data;

import java.util.Observable;


/**
 * 
 * @author Claude Hallard och Hanna Eriksson
 * Represents the 2-d game grid
 */
public class GameGrid extends Observable{
	
	private int size;
	private int[][] board; //2-dimensional array representing the game grid.
	public static final int EMPTY = 0;
	public static final int ME = 1;
	public static final int OTHER = 2;
	private int INROW = 5;

	
	/**
	 * Constructor
	 * 
	 * @param size The width/height of the game grid
	 */
	public GameGrid(int size){
		this.size = size;
		board = new int[size][size];
	}
	
	/**
	 * Reads a location of the grid
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return the value of the specified location
	 */
	public int getLocation(int x, int y){
		return board[x][y];}
	/**
	 * Returns the size of the grid
	 * 
	 * @return the grid size
	 */
	public int getSize(){
		return size;
	}
	/**
	 * Enters a move in the game grid
	 * @param x the x-position
	 * @param y the y-position
	 * @param player the player
	 * @return 's true if the insertion worked, false otherwise.
	 */
	
	public boolean move(int x, int y, int player){
		if (board[x][y] == 0) {
			board[x][y] = player;
			setChanged();
			notifyObservers();
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Clears the grid of pieces
	 */
	public void clearGrid(){
		board = new int[size][size];
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Check if a player has won
	 * 
	 * @param player the player to check for
	 * @return true if player has won, false otherwise
	 */
	public boolean isWinner(int player){
		if(horizontalWin(player) && verticalWin(player) && diagonalWin(player)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param player
	 * @return 's true if player has a horizontal win
	 */
	
	private boolean horizontalWin(int player) {
		int count = 0;
		for(int x = 0; x<size; x++) {
			for(int y = 0; y<size; y++) {
				count = count(x,y,count,player);
				if(count == INROW) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @param player
	 * @return 's true if player has won vertically
	 */
	
	private boolean verticalWin(int player) {
		int count = 0;
		for(int y = 0; y<size; y++) {
			for(int x = 0; x<size; x++) {
				count = count(x,y,count,player);
				if(count == INROW) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @param player
	 * @return 's true if player has won diagonally
	 */
	
	private boolean diagonalWin(int player) {
		int count;
		int moveX = 0;
		while (moveX < size){
			count = 0;
			for(int x = 0 + moveX, y = 0; x<size; x++, y++) {
				count = count(x,y,count,player);
				if(count == INROW) {
					return true;
				}
			}
			count = 0;
			for(int x = 0 + moveX, y = size - 1; x<size; x++, y--) {
				count = count(x,y,count,player);
				if(count == INROW) {
					return true;
				}
			}
			count = 0;
			for(int x = size - 1 - moveX, y = 0; x>=0; x--, y++) {
				count = count(x,y,count,player);
				if(count == INROW) {
					return true;
				}
			}
			count = 0;
			for(int x = size - 1 - moveX, y = size - 1; x>= 0; x--, y--) {
				count = count(x,y,count,player);
				if(count == INROW) {
					return true;
				}
			}
			moveX++;
		}
			
		return false;
	}
	
	/**
	 * 
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 * @param count counts the players rings in a row
	 * @param player the player
	 * @return 's count
	 */
	
	private int count(int x, int y, int count, int player) {
		if(board[x][y] != player) {
			count = 0;
		}
		if (board[x][y] == player) {
			count ++;
		}
		return count;
	}
	
	
}
