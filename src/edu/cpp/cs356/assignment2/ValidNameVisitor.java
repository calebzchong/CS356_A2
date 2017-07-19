package edu.cpp.cs356.assignment2;

import java.util.HashSet;
import java.util.Set;

public class ValidNameVisitor implements Visitor {

	private boolean valid;
	private Set<String> names;
	
	public ValidNameVisitor(){
		reset();
	}
	
	@Override
	public void visit(User user) {
		checkName(user);
	}

	@Override
	public void visit(UserGroup group) {
		checkName(group);
	}
	
	private void checkName(TwitterEntity t ){
		String name = t.getName();
		if ( names.contains(name) || name.contains(" ") ){
			valid = false;
		} else {
			names.add(name);
		}
	}
	
	public boolean isValid(){
		return valid;
	}

	@Override
	public void reset() {
		names = new HashSet<>();
		valid = true;
	}

}
