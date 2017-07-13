package edu.cpp.cs356.assignment2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
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
import java.util.Hashtable;

public class AdminPanel {

	private JFrame frmMiniTwitter;
	private JTextField txtUserID;
	private JTree userTree;
	private UserGroup rootGroup;
	private DefaultMutableTreeNode root;
	private JTextField txtGroupID;
	private DefaultTreeModel treeModel;
	private Hashtable<String, User> users;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel window = new AdminPanel();
					window.frmMiniTwitter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMiniTwitter = new JFrame();
		frmMiniTwitter.setResizable(false);
		frmMiniTwitter.setTitle("Mini Twitter - Admin Panel");
		frmMiniTwitter.setBounds(100, 100, 457, 266);
		frmMiniTwitter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMiniTwitter.getContentPane().setLayout(null);

		rootGroup = new UserGroup("Root");
		users = new Hashtable<String, User>();
		
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

				DefaultMutableTreeNode node = getAddingGroup();
				UserGroup addingGroup = (UserGroup)node.getUserObject();

				User newUser = new User(txtUserID.getText());
				addingGroup.add( newUser );
				node.add( new DefaultMutableTreeNode(newUser) );
				users.put(newUser.toString(), newUser );

				treeModel.reload();
				txtUserID.setText("");

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

				DefaultMutableTreeNode node = getAddingGroup();
				UserGroup addingGroup = (UserGroup)node.getUserObject();

				UserGroup newUserGroup = new UserGroup(txtGroupID.getText());
				addingGroup.add( newUserGroup );
				node.add( new DefaultMutableTreeNode(newUserGroup) );

				treeModel.reload();
				txtGroupID.setText("");
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
					UserPanel window = new UserPanel(user, users);
					window.frame.setVisible(true);
				}
			}
		});
		btnOpenUser.setBounds(210, 72, 225, 23);
		frmMiniTwitter.getContentPane().add(btnOpenUser);

		JButton btnShowUserTotal = new JButton("Show User Total");
		btnShowUserTotal.setMargin(new Insets(2, 2, 2, 2));
		btnShowUserTotal.setBounds(210, 161, 104, 23);
		frmMiniTwitter.getContentPane().add(btnShowUserTotal);

		JButton btnNewButton_1 = new JButton("Show Group Total");
		btnNewButton_1.setMargin(new Insets(2, 2, 2, 2));
		btnNewButton_1.setBounds(324, 161, 111, 23);
		frmMiniTwitter.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Show Messages");
		btnNewButton_2.setMargin(new Insets(2, 2, 2, 2));
		btnNewButton_2.setBounds(210, 195, 104, 23);
		frmMiniTwitter.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Show Positive %");
		btnNewButton_3.setMargin(new Insets(2, 2, 2, 2));
		btnNewButton_3.setBounds(324, 195, 111, 23);
		frmMiniTwitter.getContentPane().add(btnNewButton_3);
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

}
