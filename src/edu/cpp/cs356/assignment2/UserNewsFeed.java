package edu.cpp.cs356.assignment2;

import java.util.ArrayList;
import java.util.List;

/**
 * News feed class
 * Container for list of posts in user's newsfeed
 * Allows attaching observers for updates
 */
public class UserNewsFeed {
	private List<Post> feedPosts;
	private List<NewsFeedObserver> observers;
	
	public UserNewsFeed(){
		feedPosts = new ArrayList<Post>();
		observers = new ArrayList<NewsFeedObserver>();
	}

	public void addPost(Post post) {
		feedPosts.add(post);
		notifyObservers();
	}
	
	private void notifyObservers() {
		for ( Observer ob : observers ){
			ob.update();
		}
	}

	public void attachNewsFeedObserver( NewsFeedObserver ob ){
		observers.add(ob);
	}
	
	public void detachNewsFeedObserver( NewsFeedObserver ob ){
		observers.remove(ob);
	}
	
	public List<Post> getPosts(){
		return feedPosts;
	}
	
}
