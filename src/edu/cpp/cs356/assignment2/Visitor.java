package edu.cpp.cs356.assignment2;

/**
 * The visitor interface.
 * It is implemented by the CountingVisitor and PositiveVisitor classes
 */
public interface Visitor {
	public void visit(User user);	
	public void visit(UserGroup group);
	public void resetCount();
}
