import java.util.ArrayList;
import javax.swing.JTextArea;

public class Simulation {
	private ArrayList<Node> listNodes;
	private ArrayList<Integer> messageJumps;
	private GUI gui;
	
	public Simulation(GUI window){
		this.gui = window;
	}
	
	/*
	 * Iterates over a simulation process depending on the type of
	 * simulation selected
	 */
	public void step(){
		
	}
	
	/*
	 * Sums the list of number of jumps messages went through
	 * Finds the average number of jumps done between all messages
	 * 
	 * @ret integer containing average
	 */
	public int average(){
		return 0;
	}
}
