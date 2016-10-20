import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JTextArea;

import java.util.Random;

public class Simulation {
	private ArrayList<Node> listNodes;
	private ArrayList<Integer> messageJumps;
	private GUI gui;
	private SimulationType type;
	private ArrayList<Message> listMessages;
	
	public Simulation(GUI window){
		this.listNodes = new ArrayList<Node>();
		this.messageJumps = new ArrayList<Integer>();
		this.listMessages = new ArrayList<Message>();
		this.gui = window;
		
		//Temporary setup: Type is initialized at random
		this.type = SimulationType.RANDOM;
	}
	
	/*
	 * Set the message object that is created by the user to give
	 * reference for the simulation.
	 * 
	 * @param1 the message object that the user creates
	 */
	public void setMsg(Message msg){
		listMessages.add(msg);
	}
	
	/*
	 * Iterates over a simulation process depending on the type of
	 * simulation selected
	 */
	public void step(){
		
		for(Message msg : this.listMessages){
			System.out.println(this.type);
			switch(this.type){
			//User selected RANDOM step type.
			case RANDOM:
				Random nextNode = new Random();
				System.out.println("Simulation Type Selected: " + this.type);
				Node refNode = msg.getPath().getLast();
				HashSet<Node> refPath = refNode.getConnections();

				//Generate a randomly selected node from the hash set
				int index = nextNode.nextInt(refPath.size());
				Iterator<Node> iter = refPath.iterator();
				for (int i = 0; i < index; i++) {
					iter.next();
				}
				//Set the current node reference to a random node that the node is connected to
				Node currentNode = iter.next();
				msg.appendPath(currentNode);
				for(Node n : msg.getPath()){
					System.out.println(n.getDetails());
				}
				msg.incCount();
				break;

				//To Implement: Flood step type
			case FLOOD:
				break;

			default:
				System.out.println("No current type selected!");
				break;
			}
		}
	}
	
	/*
	 * Sums the list of number of jumps messages went through
	 * Finds the average number of jumps done between all messages
	 * 
	 * @ret integer containing average
	 */
	public int average(){
		int tempSum = 0;
		//Calculate total number of jumps
		for(int numJumps : this.messageJumps){
			tempSum += numJumps;
		}
		//Return average (number of jumps/size of arraylist)
		return tempSum/this.messageJumps.size();
	}
}