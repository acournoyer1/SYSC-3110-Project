import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class GUI extends JFrame{
	
	private JMenuItem addNode;
	private JMenuItem addConnection;
	private JMenuItem addMessage;
	private JMenuItem createTest;
	private JMenuItem removeNode;
	private JMenuItem removeConnection;
	private JMenuItem viewNode;
	private JMenuItem viewAverage;
	private JMenuItem randomType;
	private JTextArea statusWindow;
	private JButton stepButton;
	
	public GUI()
	{
		JTextArea t = new JTextArea();
		t.setEditable(false);
		JMenuBar jMenuBar = new JMenuBar();
		
		addNode = new JMenuItem("Node");
		addConnection = new JMenuItem("Connection");
		addMessage = new JMenuItem("Message");
		createTest = new JMenuItem("Create Test Network");
		removeNode = new JMenuItem("Node");
		removeConnection = new JMenuItem("Connection");
		viewNode = new JMenuItem("Node");
		viewAverage = new JMenuItem("Average");
		statusWindow = new JTextArea();
		stepButton = new JButton("Step");
		randomType = new JMenuItem("Random");
		
		JMenu addMenu = new JMenu("Add");
		JMenu removeMenu = new JMenu("Remove");
		JMenu viewMenu = new JMenu("View");
		JMenu simulationMenu = new JMenu("Simulation");
		JMenu typeMenu = new JMenu("Set type");
		jMenuBar.add(addMenu);
		jMenuBar.add(removeMenu);
		jMenuBar.add(viewMenu);
		jMenuBar.add(simulationMenu);
		addMenu.add(addNode);
		addMenu.add(addConnection);
		addMenu.add(addMessage);
		addMenu.addSeparator();
		addMenu.add(createTest);
		removeMenu.add(removeNode);
		removeMenu.add(removeConnection);
		viewMenu.add(viewNode);
		viewMenu.add(viewAverage);
		simulationMenu.add(typeMenu);
		typeMenu.add(randomType);
		this.setJMenuBar(jMenuBar);
		
		JSplitPane split = new JSplitPane();
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split.setTopComponent(statusWindow);
		split.setBottomComponent(stepButton);
		split.setDividerSize(1);
	    split.setEnabled(false);
		stepButton.setEnabled(false);
		statusWindow.setEnabled(false);
		removeNode.setEnabled(false);
		removeConnection.setEnabled(false);
		viewNode.setEnabled(false);
		viewAverage.setEnabled(false);
		this.add(split);
		this.setSize(600, 400);
		split.setDividerLocation((int)(this.getHeight()*0.75));
		this.setTitle("Network Simulation");
		this.setVisible(true);
	}
	
	public static void main(String args[])
	{
		GUI gui = new GUI();
	}
}
