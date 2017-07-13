package edu.cpp.cs356.assignment2;

import java.util.ArrayList;
import java.util.List;

public class UserGroup implements TwitterEntity {

	private String name;
	
	private List<TwitterEntity> entities;
	
	public UserGroup( String name ){
		this.name = name;
		entities = new ArrayList<TwitterEntity>();
	}
	
	
	public void add( TwitterEntity e ){
		entities.add(e);
	}
	

	@Override
	public String toString() {
		return name;
	}
	

}
