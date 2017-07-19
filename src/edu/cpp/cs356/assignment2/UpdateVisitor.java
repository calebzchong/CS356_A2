package edu.cpp.cs356.assignment2;

import java.util.Date;

public class UpdateVisitor implements Visitor {
	
	User lastUpdated;	
	
	@Override
	public void visit(User user) {
		if ( lastUpdated == null ){
			lastUpdated = user;
		} else {
			Date oldTime = lastUpdated.getNewsFeed().getLastUpdateTime();
			Date newTime = user.getNewsFeed().getLastUpdateTime();
			if ( oldTime.compareTo(newTime) < 0 ){
				lastUpdated = user;
			}
		}
	}

	@Override
	public void visit(UserGroup group) {
	}
	
	public User getLatest(){
		return lastUpdated;
	}
	
	@Override
	public void reset() {
		lastUpdated = null;
	}

}
