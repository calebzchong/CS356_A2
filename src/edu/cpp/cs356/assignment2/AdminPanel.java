package edu.cpp.cs356.assignment2;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.awt.Toolkit;

/**
 *  The main GUI. Contains Admin functions such as adding users and groups.
 *  It uses the singleton pattern.
 *
 */
public class AdminPanel {

	//Singleton
	private static AdminPanel singleton = new AdminPanel();

	private JFrame frmMiniTwitter;
	private JTextField txtUserID;
	private JTree userTree;
	private UserGroup rootGroup;
	private DefaultMutableTreeNode root;
	private JTextField txtGroupID;
	private DefaultTreeModel treeModel;
	private Hashtable<String, User> users;
	private Hashtable<String, UserGroup> groups;
	private Visitor countingVisitor;
	private Visitor positiveVisitor;

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	private AdminPanel() {
		initialize();
		this.frmMiniTwitter.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMiniTwitter = new JFrame();
		frmMiniTwitter.setIconImage(Toolkit.getDefaultToolkit().getImage(AdminPanel.class.getResource("/edu/cpp/cs356/assignment2/icon.png")));
		frmMiniTwitter.setResizable(false);
		frmMiniTwitter.setTitle("Mini Twitter - Admin Panel");
		frmMiniTwitter.setBounds(100, 100, 457, 266);
		frmMiniTwitter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMiniTwitter.getContentPane().setLayout(null);


		rootGroup = new UserGroup("Root");
		users = new Hashtable<String, User>();
		groups = new Hashtable<String, UserGroup>();
		countingVisitor = new CountingVisitor();
		positiveVisitor = new PositiveVisitor();

		userTree = new JTree();
		userTree.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));
		userTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		root = new DefaultMutableTreeNode(rootGroup);
		treeModel = new DefaultTreeModel( root );
		userTree.setModel( treeModel );
		userTree.setBounds(10, 13, 190, 205);
		frmMiniTwitter.getContentPane().add(userTree);

		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) userTree.getCellRenderer();
		renderer.setLeafIcon(null);
		renderer.setClosedIcon(null);
		renderer.setOpenIcon(null);

		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String userID = txtUserID.getText();
				if ( users.containsKey(userID) || groups.contains(userID) ){
					txtUserID.setText("UserID Already Exists");
				} else {
					DefaultMutableTreeNode node = getAddingGroup();
					UserGroup addingGroup = (UserGroup)node.getUserObject();

					User newUser = new User(userID);
					addingGroup.add( newUser );
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newUser);
					node.add( newNode );
					users.put(newUser.toString(), newUser );

					countingVisitor.reset();
					rootGroup.acceptVisitor(countingVisitor);

					treeModel.reload();
					userTree.expandPath(new TreePath(node.getPath()));
					txtUserID.setText("");
				}

			}
		});
		btnAddUser.setMargin(new Insets(2, 2, 2, 2));
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddUser.setBounds(340, 13, 95, 20);
		frmMiniTwitter.getContentPane().add(btnAddUser);

		txtUserID = new JTextField();
		txtUserID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					btnAddUser.doClick();
				}
			}
		});
		txtUserID.setText("User ID");
		setHintText( txtUserID, "User ID");
		txtUserID.setBounds(210, 13, 124, 20);
		frmMiniTwitter.getContentPane().add(txtUserID);
		txtUserID.setColumns(10);

		JButton btnAddGroup = new JButton("Add Group");
		btnAddGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String groupID = txtGroupID.getText();
				if ( groups.containsKey(groupID) || users.containsKey(groupID) ){
					txtGroupID.setText("Group already exists.");

				} else {
					DefaultMutableTreeNode node = getAddingGroup();
					UserGroup addingGroup = (UserGroup)node.getUserObject();
					UserGroup newUserGroup = new UserGroup(txtGroupID.getText());
					addingGroup.add( newUserGroup );
					node.add( new DefaultMutableTreeNode(newUserGroup) );
					groups.put(groupID, newUserGroup);

					countingVisitor.reset();
					rootGroup.acceptVisitor(countingVisitor);

					treeModel.reload();
					userTree.expandPath(new TreePath(node.getPath()));
					txtGroupID.setText("");
				}

			}
		});
		btnAddGroup.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddGroup.setBounds(340, 41, 95, 20);
		frmMiniTwitter.getContentPane().add(btnAddGroup);


		txtGroupID = new JTextField();
		txtGroupID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					btnAddGroup.doClick();
				}
			}
		});
		setHintText( txtGroupID, "Group ID");
		txtGroupID.setText("Group ID");
		txtGroupID.setBounds(210, 41, 124, 20);
		frmMiniTwitter.getContentPane().add(txtGroupID);
		txtGroupID.setColumns(10);

		JButton btnOpenUser = new JButton("Open User View");
		btnOpenUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = getSelectedUser();
				if ( user != null ){
					new UserPanel(user, users);
				}
			}
		});
		btnOpenUser.setBounds(210, 72, 225, 23);
		frmMiniTwitter.getContentPane().add(btnOpenUser);

		JButton btnUserTotal = new JButton("Show User Total");
		btnUserTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CountingVisitor v = (CountingVisitor)countingVisitor;
				String msg = "There are " + v.getUsersCount() + " users total.";
				JOptionPane.showMessageDialog(null, msg , "User Count", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnUserTotal.setMargin(new Insets(2, 2, 2, 2));
		btnUserTotal.setBounds(210, 127, 104, 23);
		frmMiniTwitter.getContentPane().add(btnUserTotal);

		JButton btnGroupTotal = new JButton("Show Group Total");
		btnGroupTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CountingVisitor v = (CountingVisitor)countingVisitor;
				String msg = "There are " + v.getGroupsCount() + " groups total.";
				JOptionPane.showMessageDialog(null, msg , "Group Count", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnGroupTotal.setMargin(new Insets(2, 2, 2, 2));
		btnGroupTotal.setBounds(324, 127, 111, 23);
		frmMiniTwitter.getContentPane().add(btnGroupTotal);

		JButton btnMessagesCount = new JButton("Message Count");
		btnMessagesCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				countingVisitor.reset();
				rootGroup.acceptVisitor(countingVisitor);
				CountingVisitor v = (CountingVisitor)countingVisitor;
				String msg = "There are " + v.getMessageCount() + " messages total.";
				JOptionPane.showMessageDialog(null, msg , "Message Count", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnMessagesCount.setMargin(new Insets(2, 2, 2, 2));
		btnMessagesCount.setBounds(210, 161, 104, 23);
		frmMiniTwitter.getContentPane().add(btnMessagesCount);

		JButton btnPositivePercent = new JButton("Positive Msg %");
		btnPositivePercent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				positiveVisitor.reset();
				rootGroup.acceptVisitor(positiveVisitor);
				PositiveVisitor v = (PositiveVisitor)positiveVisitor;
				String msg = String.format("%d%% of messages are positive.", (int)(v.getPositivePercentage()*100) );
				JOptionPane.showMessageDialog(null, msg , "Positive Messages", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnPositivePercent.setMargin(new Insets(2, 2, 2, 2));
		btnPositivePercent.setBounds(324, 161, 111, 23);
		frmMiniTwitter.getContentPane().add(btnPositivePercent);

		JButton btnCheckNames = new JButton("Check Names");
		btnCheckNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidNameVisitor checkNameVisitor = new ValidNameVisitor();
				rootGroup.acceptVisitor(checkNameVisitor);
				String msg;
				if ( checkNameVisitor.isValid() ) {
					msg = "All names are valid.";
				} else {
					msg = "Invalid names were found.";
				}
				JOptionPane.showMessageDialog(null, msg , "User ID Validity", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnCheckNames.setMargin(new Insets(2, 2, 2, 2));
		btnCheckNames.setBounds(210, 195, 104, 23);
		frmMiniTwitter.getContentPane().add(btnCheckNames);

		JButton btnGetLatest = new JButton("Last Updated");
		btnGetLatest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateVisitor visitor = new UpdateVisitor();
				rootGroup.acceptVisitor( visitor);
				User user = visitor.getLatest();
				SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy");
				String msg = "The last updated user was " + user.getName() + " who was last updated at "
						+ ft.format(user.getNewsFeed().getLastUpdateTime() );
				JOptionPane.showMessageDialog(null, msg , "Last Updated", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnGetLatest.setMargin(new Insets(2, 2, 2, 2));
		btnGetLatest.setBounds(324, 195, 111, 23);
		frmMiniTwitter.getContentPane().add(btnGetLatest);
	}

	private void setHintText( JTextField txt, String msg){
		txt.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if(txt.getText().trim().equals(""))
					txt.setText(msg);
			}

			public void focusGained(FocusEvent e) {
				if( txt.getText().trim().equals(msg))
					txt.setText("");
			}
		});
	}

	private User getSelectedUser(){
		User user = null;
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)userTree.getLastSelectedPathComponent();
		if ( node != null ){
			try {
				user = (User)node.getUserObject();
			} catch ( ClassCastException ex ){
			}
		}
		return user;
	}

	private DefaultMutableTreeNode getAddingGroup() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)userTree.getLastSelectedPathComponent();
		if ( node == null ){
			node = root;
		}
		try {
			UserGroup addingGroup = (UserGroup)node.getUserObject();
		} catch ( ClassCastException ex ){
			node = (DefaultMutableTreeNode)node.getParent();
		}
		return node;
	}

	public static AdminPanel getInstance() {
		return singleton;
	}
}
