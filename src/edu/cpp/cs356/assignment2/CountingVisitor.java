package edu.cpp.cs356.assignment2;

/**
 * Visitor for counting stuff.
 * Counts total number of users, groups, and messages.
 */
public class CountingVisitor implements Visitor{

	private int usersCount;
	private int groupsCount;
	private int msgCount;
	
	public CountingVisitor(){
		usersCount = 0;
		groupsCount = 0;
		msgCount = 0;
	}
	
	public void visit(User user) {
		usersCount++;
		msgCount += user.getPosts().size();
	}
	
	public void visit(UserGroup group){
		groupsCount++;
	}
	
	public int getUsersCount(){
		return usersCount;
	}
	
	public int getGroupsCount(){
		return groupsCount;
	}
	
	public int getMessageCount(){
		return msgCount;
	}
	
	public void resetCount(){
		usersCount = 0;
		groupsCount = 0;
		msgCount = 0;
	}
	
}
