package edu.cpp.cs356.assignment2;

import java.util.Date;

/**
 *  This interface is implemented by the User and UserGroup classes.
 *  It uses composite design pattern.
 *
 */
public interface TwitterEntity {
	public String toString();
	public String getName();
	public void acceptVisitor(Visitor v);
	public Date getCreationTime();
}
