package lab4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lab4.data.GameGrid;


/**
 * 
 * @author Claude Hallard och Hanna Eriksson
 *A panel providing a graphical view of the game board. Repaints when actions are performed.
 */
public class GamePanel extends JPanel implements Observer{

	private final int UNIT_SIZE = 20;
	private GameGrid grid;
	
	/**
	 * The constructor
	 * 
	 * @param grid The grid that is to be displayed
	 */
	public GamePanel(GameGrid grid){
		this.grid = grid;
		grid.addObserver(this);
		Dimension d = new Dimension(grid.getSize()*UNIT_SIZE+1, grid.getSize()*UNIT_SIZE+1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
	}

	/**
	 * Returns a grid position given pixel coordinates
	 * of the panel
	 * 
	 * @param x the x coordinates
	 * @param y the y coordinates
	 * @return an integer array containing the [x, y] grid position
	 */
	public int[] getGridPosition(int x, int y){
		int x_pos = x/UNIT_SIZE;
		int y_pos = y/UNIT_SIZE;
		int[] GridP = new int[2];
		GridP[0] = x_pos;
		GridP[1] = y_pos;
		return GridP;
		
	}
	/**
	 * Repaints the board
	 */
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
	/**
	 * Paints the board and possible rings
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int x=0, y=0; x < grid.getSize() * UNIT_SIZE+1; x+=UNIT_SIZE, y+=UNIT_SIZE) {
			g.drawLine(0, y, grid.getSize()*UNIT_SIZE, y);
			g.drawLine(x, 0, x, grid.getSize()*UNIT_SIZE);
		}
		
		
	}
	
	public void paintDots(Graphics g, int x, int y, GomokuGameState game) {
		
	}
	
}
