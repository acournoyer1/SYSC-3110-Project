
public class Message {
	private Node src;
	private Node dest;
	private int count;
	
	
	
	/*
	 * Iterates over a simulation process depending on the type of
	 * simulation selected
	 */
	
	
	public Message(Node src, Node dest){
		this.src = src;
		this.dest = dest;
		count = 0;
		
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
