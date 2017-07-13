package edu.cpp.cs356.assignment2;

import javax.swing.JFrame;
import java.awt.List;
import java.util.Hashtable;

import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class UserPanel {

	protected JFrame frame;
	private User user;
	private Hashtable<String, User> users;
	private JTextField txtUserId;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserPanel window = new UserPanel();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public UserPanel( User user, Hashtable<String, User> users) {
		this.user = user;
		this.users = users;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle( user.toString() + "'s User Panel");
		frame.setResizable(false);
		frame.setBounds(100, 100, 350, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea txtPost = new JTextArea();
		txtPost.setBounds(1, 1, 11, 58);
		txtPost.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPost.setText("Write post here");
		txtPost.setLineWrap(true);
//		txtPost.setBounds(0, 264, 25, 17);
		frame.getContentPane().add(txtPost);
		
		JScrollPane scrollPane = new JScrollPane(txtPost);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 145, 252, 48);
		frame.getContentPane().add(scrollPane);
		
		DefaultListModel<User> listModel = new DefaultListModel<>();
		for ( User f : user.getFollowers() ){
			listModel.addElement(f);
		}
		JList<User> followingList = new JList<>(listModel);
		followingList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		followingList.setBounds(10, 39, 314, 95);
		frame.getContentPane().add(followingList);
		
		JButton btnFollow = new JButton("Follow");
		btnFollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User follow = users.get( txtUserId.getText() );
				if ( follow != null ){
					user.addFollower( follow );
					listModel.addElement(follow);
					int index = followingList.getModel().getSize()-1;
					followingList.setSelectedIndex(index);
					followingList.ensureIndexIsVisible(index);
					txtUserId.setText("" +index);
				} else {
					txtUserId.setText("User ID not found");
				}
				
			}
		});
		btnFollow.setBounds(251, 10, 73, 23);
		frame.getContentPane().add(btnFollow);
		
		txtUserId = new JTextField();
		txtUserId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUserId.getText().trim().equals(""))
					txtUserId.setText("Enter User ID");
			}

			@Override
			public void focusGained(FocusEvent e) {
				if( txtUserId.getText().trim().equals("Enter User ID"))
					txtUserId.setText("");
			}
		});
		txtUserId.setBounds(66, 11, 177, 21);
		frame.getContentPane().add(txtUserId);
		txtUserId.setColumns(10);
		
		JButton btnPost = new JButton("Post");
		btnPost.setMargin(new Insets(2, 2, 2, 2));
		btnPost.setBounds(272, 145, 52, 48);
		frame.getContentPane().add(btnPost);
		
		JLabel lblFollowing = new JLabel("Following");
		lblFollowing.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFollowing.setBounds(10, 14, 52, 14);
		frame.getContentPane().add(lblFollowing);
		

	}
	
	private void setHintText( JTextField txt, String msg){
		txt.addFocusListener(new FocusAdapter() {

		});
	}
}
