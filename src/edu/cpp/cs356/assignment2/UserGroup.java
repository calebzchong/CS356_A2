package edu.cpp.cs356.assignment2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Composite Design pattern for User 
 * It is a group of users.
 */
public class UserGroup implements TwitterEntity {

	private String name;
	private Date creationTime;
	private List<TwitterEntity> entities;
	
	public UserGroup( String name ){
		this.name = name;
		entities = new ArrayList<TwitterEntity>();
		creationTime = new Date();
	}
	
	public void add( TwitterEntity e ){
		entities.add(e);
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public void acceptVisitor( Visitor v) {
		v.visit(this);
		for ( TwitterEntity u : entities ){
			u.acceptVisitor(v);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Date getCreationTime() {
		return creationTime;
	}
	

}
