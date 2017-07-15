package edu.cpp.cs356.assignment2;

public interface Visitor {
	public void visit(User user);
	
	public void visit(UserGroup group);
	public void resetCount();
}
