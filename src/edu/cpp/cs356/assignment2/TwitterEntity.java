package edu.cpp.cs356.assignment2;

/**
 *  This interface is implemented by the User and UserGroup classes.
 *  It uses composite design pattern.
 *
 */
public interface TwitterEntity {
	public String toString();
	public void acceptVisitor(Visitor v);
}
