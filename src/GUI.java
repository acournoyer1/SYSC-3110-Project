import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
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
		this.setLocationRelativeTo(null);
		setUpListeners();
		this.setVisible(true);
	}
	
	private void setUpListeners()
	{
		addNode.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new AddNode();
			}
		});
		
		addConnection.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new AddConnection();
			}
		});
		
		addMessage.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new AddMessage();
			}
		});
		removeNode.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new RemoveNode();
			}
		});
		removeConnection.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new RemoveConnection();
			}
		});
	}
	
	public static void main(String args[])
	{
		new GUI();
	}
	
	private class AddNode extends JFrame
	{
		private JTextField nameField;
		private JButton addButton;
		private JButton cancelButton;
		
		public AddNode()
		{
			JLabel label = new JLabel("Name: ");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			JSplitPane split = new JSplitPane();
			JSplitPane top = new JSplitPane();
			JPanel bottom = new JPanel();
			nameField = new JTextField();
			addButton = new JButton("Add");
			cancelButton = new JButton("Cancel");
			bottom.add(addButton);
			bottom.add(cancelButton);
			split.setOrientation(JSplitPane.VERTICAL_SPLIT);
			split.setTopComponent(top);
			split.setBottomComponent(bottom);
			split.setEnabled(false);
			split.setDividerSize(1);
			top.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			top.setLeftComponent(label);
			top.setRightComponent(nameField);
			top.setEnabled(false);
			top.setDividerSize(1);
			this.add(split);
			this.setSize(200,100);
			this.setResizable(false);
			this.setTitle("Add Node");
			split.setDividerLocation((int)(this.getHeight()*0.35));
			top.setDividerLocation((int)(this.getWidth()*0.35));
			this.setLocationRelativeTo(null);
			setUpListeners();
			this.setVisible(true);
		}
		
		private void setUpListeners()
		{
			cancelButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					dispose();	
				}			
			});
		}
	}
	
	private class AddConnection extends JFrame
	{
		private JComboBox<Node> firstNode;
		private JComboBox<Node> secondNode;
		private JButton addButton;
		private JButton cancelButton;
		
		public AddConnection()
		{
			firstNode = new JComboBox<Node>();
			secondNode = new JComboBox<Node>();
			addButton = new JButton("Add");
			cancelButton = new JButton("Cancel");
			
			this.setTitle("Add Connection");
			JPanel top = new JPanel();
			top.add(firstNode);
			top.add(secondNode);
			JPanel bottom = new JPanel();
			bottom.add(addButton);
			bottom.add(cancelButton);
			JSplitPane split = new JSplitPane();
			split.setOrientation(JSplitPane.VERTICAL_SPLIT);
			split.setTopComponent(top);
			split.setBottomComponent(bottom);
			split.setDividerSize(1);
			this.setSize(200, 100);
			this.setResizable(false);
			split.setDividerLocation((int)(this.getHeight()*0.35));
			this.add(split);
			this.setLocationRelativeTo(null);
			setUpListeners();
			this.setVisible(true);
		}
		
		public void setUpListeners()
		{
			cancelButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}
			});
		}
	}
	
	private class AddMessage extends JFrame
	{
		private JComboBox<Node> source;
		private JComboBox<Node> destination;
		private JButton addButton;
		private JButton cancelButton;
		
		public AddMessage()
		{
			source = new JComboBox<Node>();
			destination = new JComboBox<Node>();
			addButton = new JButton("Add");
			cancelButton = new JButton("Cancel");
			
			this.setTitle("Add Connection");
			JPanel top = new JPanel();
			top.add(new JLabel("Source: "));
			top.add(source);
			top.add(new JLabel("Destination: "));
			top.add(destination);
			JPanel bottom = new JPanel();
			bottom.add(addButton);
			bottom.add(cancelButton);
			JSplitPane split = new JSplitPane();
			split.setOrientation(JSplitPane.VERTICAL_SPLIT);
			split.setTopComponent(top);
			split.setBottomComponent(bottom);
			split.setDividerSize(1);
			this.setSize(300, 100);
			this.setResizable(false);
			split.setDividerLocation((int)(this.getHeight()*0.35));
			this.add(split);
			this.setLocationRelativeTo(null);
			setUpListeners();
			this.setVisible(true);
		}
		
		public void setUpListeners()
		{
			cancelButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}
			});
		}
	}
	
	private class RemoveNode extends JFrame
	{
		private JComboBox<Node> node;
		private JButton addButton;
		private JButton cancelButton;
		
		public RemoveNode()
		{
			node = new JComboBox<Node>();
			addButton = new JButton("Add");
			cancelButton = new JButton("Cancel");
			
			this.setTitle("Remove Connection");
			JPanel top = new JPanel();
			top.add(node);
			JPanel bottom = new JPanel();
			bottom.add(addButton);
			bottom.add(cancelButton);
			JSplitPane split = new JSplitPane();
			split.setOrientation(JSplitPane.VERTICAL_SPLIT);
			split.setTopComponent(top);
			split.setBottomComponent(bottom);
			split.setDividerSize(1);
			this.setSize(200, 100);
			this.setResizable(false);
			split.setDividerLocation((int)(this.getHeight()*0.35));
			this.add(split);
			this.setLocationRelativeTo(null);
			setUpListeners();
			this.setVisible(true);
		}
		
		public void setUpListeners()
		{
			cancelButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}
			});
		}
	}
	
	private class RemoveConnection extends JFrame
	{
		private JComboBox<Connection> connection;
		private JButton addButton;
		private JButton cancelButton;
		
		public RemoveConnection()
		{
			connection = new JComboBox<Connection>();
			addButton = new JButton("Add");
			cancelButton = new JButton("Cancel");
			
			this.setTitle("Remove Connection");
			JPanel top = new JPanel();
			top.add(connection);
			JPanel bottom = new JPanel();
			bottom.add(addButton);
			bottom.add(cancelButton);
			JSplitPane split = new JSplitPane();
			split.setOrientation(JSplitPane.VERTICAL_SPLIT);
			split.setTopComponent(top);
			split.setBottomComponent(bottom);
			split.setDividerSize(1);
			this.setSize(200, 100);
			this.setResizable(false);
			split.setDividerLocation((int)(this.getHeight()*0.35));
			this.add(split);
			this.setLocationRelativeTo(null);
			setUpListeners();
			this.setVisible(true);
		}
		
		public void setUpListeners()
		{
			cancelButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}
			});
		}
	}
}
