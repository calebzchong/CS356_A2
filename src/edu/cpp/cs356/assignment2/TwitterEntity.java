package edu.cpp.cs356.assignment2;

public interface TwitterEntity {
	public String toString();
	public void acceptVisitor(Visitor v);
}
