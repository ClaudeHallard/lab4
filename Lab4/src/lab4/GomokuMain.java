package lab4;
import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;
import lab4.gui.GomokuGUI;
/**
 * 
 * @author Claude Hallard och Hanna Eriksson
 * The main class
 */
 
public class GomokuMain {
	//Takes an argument which will be used as a portnumber. If no argument is given, the portnumber will be 4000.
	//The portnumber will be used in the creation of a new GomokuClient-object. 
	public static void main(String[] args) { 
		int portNumber;
		try {
			portNumber = Integer.parseInt(args[0]);
			System.out.println(portNumber);
		}
		catch(Exception e) {
			portNumber = 4000;
		}
		GomokuClient client = new GomokuClient(portNumber);
		GomokuGameState state = new GomokuGameState(client);
		GomokuGUI gui = new GomokuGUI(state, client);
	}
}
