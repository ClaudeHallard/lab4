package lab4.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;

/**
 * 
 * @author Claude Hallard och Hanna Eriksson
 * The GUI class builds up the layout and interprets the actions performed by the user
 */
public class GomokuGUI implements Observer{

	private GomokuClient client;
	private GomokuGameState gamestate;
	private JFrame MyFrame;
	private GamePanel GameGridPanel;
	private JLabel messageLabel;
	private JButton connectButton, newGameButton, disconnectButton;
	
	
	
	/**
	 * The constructor
	 * 
	 * @param g   The game state that the GUI will visualize
	 * @param c   The client that is responsible for the communication
	 */
	public GomokuGUI(GomokuGameState g, GomokuClient c){
		this.client = c;
		this.gamestate = g;
		client.addObserver(this);
		gamestate.addObserver(this);
		MyFrame = new JFrame("Gomoku");
		GameGridPanel = new GamePanel(gamestate.getGameGrid());
		messageLabel = new JLabel();
		connectButton = new JButton("Connect");
		newGameButton = new JButton("New Game");
		disconnectButton = new JButton("Disconnect");
		Box hBox = Box.createHorizontalBox();
		hBox.add(connectButton);
		hBox.add(newGameButton);
		hBox.add(disconnectButton);
		Box vBox = Box.createVerticalBox();
		vBox.add(GameGridPanel);
		vBox.add(hBox);
		vBox.add(messageLabel);
		MyFrame.add(vBox);
		MyFrame.pack();
		MyFrame.setVisible(true);
		
		// Connect buttons to listeners
		connectButton.addActionListener(
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ConnectionWindow(client);
				
			}
		}
		);
		
		newGameButton.addActionListener(
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamestate.newGame();
				
			}
		} 
		);
		
		disconnectButton.addActionListener(
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamestate.disconnect();
			}
		}
		);
		
		GameGridPanel.addMouseListener(new anonymous());
		
		// Reacts to mouse-clicks. Gets the x and y-coordinates and converts from pixel to indexes in our board.
		// Calls the move method with the x and y-values.
		class anonymous extends MouseAdapter{	
			public void mouseClicked(MouseEvent e) {
				try {
					int x = e.getX();
					int y = e.getY();
					int[] xy = GameGridPanel.getGridPosition(x, y);
					gamestate.move(xy[0], xy[1]);

				} catch(ArrayIndexOutOfBoundsException ex) {}
				
				
				
			}
			
			
			
		   
		}
		
		
		
		
		
	
	}
	
	
	
	
	public void update(Observable arg0, Object arg1) {
		
		// Update the buttons if the connection status has changed
		if(arg0 == client){
			if(client.getConnectionStatus() == GomokuClient.UNCONNECTED){
				connectButton.setEnabled(true);
				newGameButton.setEnabled(false);
				disconnectButton.setEnabled(false);
			}else{
				connectButton.setEnabled(false);
				newGameButton.setEnabled(true);
				disconnectButton.setEnabled(true);
			}
		}
		
		// Update the status text if the gamestate has changed
		if(arg0 == gamestate){
			messageLabel.setText(gamestate.getMessageString());
		}
		
	}
	
}