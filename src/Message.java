
public class Message {
	private Node src;
	private Node dest;
	private int count;
	
	
	
	/*
	 * Iterates over a simulation process depending on the type of
	 * simulation selected
	 */
	public Message(){
		
	}
	
	public Message(Node src, Node dest){
		
		
	}
	
	
	
	public Node getSrc() {
		return src;
	}
	
	public void setSrc(Node src) {
		this.src = src;
	}
	
	public Node getDest() {
		return dest;
	}
	
	public void setDest(Node dest) {
		this.dest = dest;
	}
	
	public int getCount() {
		return count;
	}
	
	public void incCount() {
		count++;
	}
	
}
