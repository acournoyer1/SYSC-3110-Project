import java.util.LinkedList;

public class Message {
	private Node src;
	private Node dest;
	private int count;
	private LinkedList<Node> nodePath;
	
	
	public Message(Node src, Node dest){
		this.src = src;
		this.dest = dest;
		count = 0;
		this.nodePath = new LinkedList<Node>();
	}
	
	public void appendPath(Node node){
		this.nodePath.add(node);
	}
	

	public LinkedList<Node> getPath(){
		return nodePath;
	}
	
	public Node getSrc() {
		return src;
	}
	
	
	public Node getDest() {
		return dest;
	}
	
	
	
	public int getCount() {
		return count;
	}
	
	public void incCount() {
		count++;
	}
	
}
