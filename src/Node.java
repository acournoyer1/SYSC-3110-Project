import java.util.LinkedList;

public class Node {
	private String name;
	private LinkedList<Connection> connections;
	
	
	
	public Node(String name){
		
		this.name = name;
		
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public LinkedList<Connection> getConnections() {
		return connections;
	}



	public void setConnections(LinkedList<Connection> connections) {
		this.connections = connections;
	}



	@Override
	public String toString() {
		return "Node [Name = " + name + ", connection 1 = " + connections.getFirst() + " and connection 2 = " + connections.getLast() + "]\n";
	}
	
	
	
	
}
