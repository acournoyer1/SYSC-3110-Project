import java.util.*;

public class Node {
	private String name;
	private HashSet<Node> connections;
	
	
	
	/*
	 * Create a node that has connections to other nodes 
	 * held in a linkedList
	 */
	public Node(String name){
		this.name = name;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public HashSet<Node> getConnections() {
		return connections;
	}

	public void addConnection(Node n)
	{
		connections.add(n);
		n.connections.add(this);
	}

	public String getDetails()
	{
		String s = "Name: = " + name + "\n"
				+ "\tConnections: ";
		if(connections.size() == 0) return s + "no connections.";
		else
		{
			for(Node n: connections)
			{
				s += n.name + " ";
			}
		    return s;
		}
	}

	@Override
	public String toString() {
		return name;
	}
	
	
	
	
}
