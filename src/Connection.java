
public class Connection {
	private Node firstNode;
	private Node secondNode;
	
	public Connection(Node n1, Node n2)
	{
		firstNode = n1;
		secondNode = n2;
	}
	
	public Node getFirstNode()
	{
		return firstNode;
	}
	
	public Node getSecondNode()
	{
		return secondNode;
	}
	
	public String toString()
	{
		return firstNode.toString() + " < - > " + secondNode.toString();
	}
	
	public boolean contains(Node n)
	{
		return firstNode == n || secondNode == n;
	}
	
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(!(o instanceof Connection)) return false;
		Connection c = (Connection)o;
		return c.firstNode.equals(this.firstNode) && c.secondNode.equals(this.secondNode);
	}
}
