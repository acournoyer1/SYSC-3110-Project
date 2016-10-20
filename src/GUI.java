
import java.awt.Font;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;

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
	private JCheckBoxMenuItem viewCommand;
	private JTextField commandField;
	private JTextArea statusWindow;
	private JButton stepButton;
	private JSplitPane split;
	private JSplitPane commandSplit;
	private CommandParser parser;
	private JScrollPane scrollPane;
	
	private LinkedList<Node> nodes;
	
	private final Font BOLD_FONT = new Font("Dialog", Font.BOLD, 12);
	
	public GUI()
	{
		nodes = new LinkedList<Node>();
		
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
		viewCommand = new JCheckBoxMenuItem("Command Line", false);
		viewCommand.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK));
		commandField = new JTextField();
		commandSplit = new JSplitPane();
		parser = new CommandParser();
		
		JMenu addMenu = new JMenu("Add");
		JMenu removeMenu = new JMenu("Remove");
		JMenu viewMenu = new JMenu("View");
		JMenu simulationMenu = new JMenu("Simulation");
		JMenu typeMenu = new JMenu("Set type");
		JMenu viewToolbars = new JMenu("Toolbars");
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
		viewMenu.add(viewToolbars);
		viewToolbars.add(viewCommand);
		simulationMenu.add(typeMenu);
		typeMenu.add(randomType);
		this.setJMenuBar(jMenuBar);
		
		scrollPane = new JScrollPane(statusWindow);
		scrollPane.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
		
		split = new JSplitPane();
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split.setTopComponent(scrollPane);
		split.setBottomComponent(stepButton);
		split.setDividerSize(1);
	    split.setEnabled(false);
	    
	    commandSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
	    commandSplit.setTopComponent(commandField);
	    commandSplit.setDividerSize(5);
	    commandSplit.setEnabled(false);
	    
		stepButton.setEnabled(false);
		removeNode.setEnabled(false);
		removeConnection.setEnabled(false);
		viewNode.setEnabled(false);
		viewAverage.setEnabled(false);
		statusWindow.setEditable(false);
		commandField.setEditable(false);
		refresh();
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
		viewCommand.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(viewCommand.isSelected())
				{
					commandSplit.setBottomComponent(scrollPane);
					split.setTopComponent(commandSplit);
					commandSplit.setDividerLocation(30);
					split.setDividerLocation((int)(GUI.this.getHeight()*0.75));
					commandField.setFont(BOLD_FONT);
					commandField.setText("");
					commandField.setEditable(true);
					commandField.requestFocusInWindow();
				}
				else
				{
					commandField.setEditable(false);
					split.setTopComponent(scrollPane);
					split.setDividerLocation((int)(GUI.this.getHeight()*0.75));
				}
			}
		});
		commandField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					parser.parse(commandField.getText());
					commandField.setText("");
				}
			}	
		});
	}
	
	private void refresh()
	{
		if(nodes.size() < 2)
		{
			addConnection.setEnabled(false);
			addMessage.setEnabled(false);
		}
		else
		{
			addConnection.setEnabled(true);
			addMessage.setEnabled(true);
		}
		if(nodes.size() == 0)
		{
			removeNode.setEnabled(false);
		}
		else
		{
			removeNode.setEnabled(true);
		}
	}
	
	private class CommandParser
	{
		public void parse(String s)
		{
			String[] words = s.split(" ");
			if (words.length == 0){}
			else if(words.length == 1)
			{
				if(words[0].equalsIgnoreCase("help"))
				{
					statusWindow.append("The commands in this program are:\n"
							+ "\tHELP\n"
							+ "\t\tShows all available commands.\n"
							+ "\tADD NODE\n"
							+ "\t\tOpens the window to add nodes.\n"
							+ "\tADD NODE name\n"
							+ "\t\tAdds a new node with the provided name.\n"
							+ "\tADD CONNECTION\n"
							+ "\t\tOpens the window to add connections.\n"
							+ "\tADD CONNECTION node1 node2\n"
							+ "\t\tAdds a connection between the two named nodes.\n"
							+ "\tADD MESSAGE\n"
							+ "\t\tOpens the window to add messages.\n"
							+ "\tADD MESSAGE source destination\n"
							+ "\t\tCreates a new message that will travel between the source and destination nodes.\n"
							+ "\tREMOVE NODE\n"
							+ "\t\tOpens the window to remove nodes.\n"
							+ "\tREMOVE NODE node\n"
							+ "\t\tRemoves the named node and all its connections\n"
							+ "\tREMOVE CONNECTION\n"
							+ "\t\tOpens the window to remove connections.\n"
							+ "\tREMOVE CONNECTION node1 node2\n"
							+ "\t\tRemoves the connection between the two named nodes.\n"
							+ "\tVIEW NODE\n"
							+ "\t\tDisplays all information about a node.\n"
							+ "\tAVERAGE\n"
							+ "\t\tDisplays the average number of jumps a message had to take before reaching it's destination.\n"
							+ "*** All commands are not case sensitive ***\n");
				}
				else if(words[0].equalsIgnoreCase("average"))
				{
					statusWindow.append("The average number of jumps is \n");
				}
				else if(words[0].equalsIgnoreCase("test"))
				{
					statusWindow.append("The test network has been built.\n");
				}
				else
				{
					statusWindow.append("Invalid comand, for all commands type 'help'.\n");
				}
			}
			else if(words[0].equalsIgnoreCase("add"))
			{
				if(words[1].equalsIgnoreCase("node"))
				{
					if(words.length == 2)
					{
						new AddNode();
					}
					else if(words.length != 3)
					{
						statusWindow.append("The add node command must be followed by either no words or 1 word, the name of the node.\n");
					}
					else
					{
						statusWindow.append("Node " + words[2] + " has been added.\n");
						//actually add the node
					}
				}
				else if(words[1].equalsIgnoreCase("connection"))
				{
					if(words.length == 2)
					{
						new AddConnection();
					}
					else if(words.length != 4)
					{
						statusWindow.append("The add connection command must be followed by either no words or 2 words, the names of the nodes the connection connects.\n");
					}
					else
					{
						statusWindow.append("A connection has been added between nodes " + words[2] + " and " + words[3] + ".\n");
						//actually add the node
					}
				}
				else if(words[1].equalsIgnoreCase("message"))
				{
					if(words.length == 2)
					{
						new AddMessage();
					}
					else if(words.length != 4)
					{
						statusWindow.append("The add message command must be followed by either no words or 2 words, the names of the source and destination nodes.\n");
					}
					else
					{
						statusWindow.append("A message has been added that will travel between nodes " + words[2] + " and " + words[3] + ".\n");
						//actually add the message
					}
				}
				else
				{
					statusWindow.append("Invalid comand, for all commands type 'help'.\n");
				}
			}
			else if(words[0].equalsIgnoreCase("remove"))
			{
				if(words[1].equalsIgnoreCase("node"))
				{
					if(words.length == 2)
					{
						new RemoveNode();
					}
					else if(words.length != 3)
					{
						statusWindow.append("The remove node command must be followed by either no words or 1 word, the name of the node.\n");
					}
					else
					{
						statusWindow.append("Node " + words[2] + " has been removed.\n");
						//actually remove the node
					}
				}
				else if(words[1].equalsIgnoreCase("connection"))
				{
					if(words.length == 2)
					{
						new RemoveConnection();
					}
					else if(words.length != 4)
					{
						statusWindow.append("The add connection command must be followed by either no words or 2 words, the names of the nodes the connection connects.\n");
					}
					else
					{
						statusWindow.append("A connection has been removed between node " + words[2] + " and " + words[3] + ".\n");
						//actually remove the connection
					}
				}
				else
				{
					statusWindow.append("Invalid comand, for all commands type 'help'.\n");
				}
			}
			else if(words[0].equalsIgnoreCase("view"))
			{
				if(words[1].equalsIgnoreCase("node"))
				{
					if(words.length == 2)
					{
						new ViewNode();
					}
					else if(words.length != 3)
					{
						statusWindow.append("The view node command must be followed by either no words or 1 word, the name of the node.\n");
					}
					else
					{
						statusWindow.append("Node " + words[2] + " has been viewed.\n");
						//actually print the node's info
					}
				}
			}
		}
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
			addButton.setEnabled(false);
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
			addButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					nodes.add(new Node(nameField.getText()));
					statusWindow.append("Node " + nameField.getText() + " has been added.");
					refresh();
					dispose();
				}		
			});
			nameField.addKeyListener(new KeyAdapter()
			{
				public void keyPressed(KeyEvent e)
				{
					if(!(e.getKeyCode() == KeyEvent.VK_ENTER))
					{
						addButton.setEnabled(true);
					}
					else if(e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						addButton.doClick();
					}
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
			addButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					//Actually implement the add
					dispose();
				}		
			});
			firstNode.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					secondNode.requestFocus();
				}		
			});
			secondNode.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					addButton.doClick();
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
			addButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					//Actually implement the add
					dispose();
				}		
			});
			//Possibly implement enter shortcut
		}
	}
	
	private class RemoveNode extends JFrame
	{
		private JComboBox<Node> node;
		private JButton removeButton;
		private JButton cancelButton;
		
		public RemoveNode()
		{
			node = new JComboBox<Node>(nodes.toArray(new Node[nodes.size()]));
			removeButton = new JButton("Remove");
			cancelButton = new JButton("Cancel");
			
			this.setTitle("Remove Connection");
			JPanel top = new JPanel();
			top.add(node);
			JPanel bottom = new JPanel();
			bottom.add(removeButton);
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
			removeButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					nodes.remove((Node)node.getSelectedItem());
					statusWindow.append("Node " + ((Node)node.getSelectedItem()).getName() + " has been removed.");
					dispose();
				}		
			});
		}
	}
	
	private class RemoveConnection extends JFrame
	{
		private JComboBox<Connection> connection;
		private JButton removeButton;
		private JButton cancelButton;
		
		public RemoveConnection()
		{
			connection = new JComboBox<Connection>();
			removeButton = new JButton("Remove");
			cancelButton = new JButton("Cancel");
			
			this.setTitle("Remove Connection");
			JPanel top = new JPanel();
			top.add(connection);
			JPanel bottom = new JPanel();
			bottom.add(removeButton);
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
			removeButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					//Actually implement the remove
					dispose();
				}		
			});
			//Possibly implement enter shortcut
		}
	}
	
	private class ViewNode extends JFrame
	{
		private JComboBox<Node> node;
		private JButton viewButton;
		private JButton cancelButton;
		
		public ViewNode()
		{
			node = new JComboBox<Node>();
			viewButton = new JButton("View");
			cancelButton = new JButton("Cancel");
			
			this.setTitle("View Node");
			JPanel top = new JPanel();
			top.add(node);
			JPanel bottom = new JPanel();
			bottom.add(viewButton);
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
			viewButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					//Actually implement the view
					dispose();
				}		
			});
			//Possibly implement enter shortcut
		}
	}
	
	public static void main(String args[])
	{
		new GUI();
	}
}
