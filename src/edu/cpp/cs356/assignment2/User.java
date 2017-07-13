package edu.cpp.cs356.assignment2;

import java.util.ArrayList;
import java.util.List;

public class User implements TwitterEntity{
	
	private String name;
	private List<User> followers;
	
	public User( String name ){
		this.name = name;
		followers = new ArrayList<>();
	}
	
	public void addFollower( User user ){
		followers.add(user);
	}
	
	public List<User> getFollowers(){
		return followers;
	}

	@Override
	public String toString() {
		return name;
	}
}
