package edu.cpp.cs356.assignment2;

import java.util.ArrayList;
import java.util.List;

public class User implements TwitterEntity{
	
	private String name;
	private List<User> following;
	private List<PostObserver> postObservers;
	private List<Post> posts;
	private UserNewsFeed newsFeed;
	
	public User( String name ){
		this.name = name;
		following = new ArrayList<>();
		postObservers = new ArrayList<>();
		posts = new ArrayList<>();
		newsFeed = new UserNewsFeed();
	}
	
	public void follow( User user ){
		following.add(user);
		user.postObservers.add( new PostObserver(user.posts, newsFeed ) );
	}
	
	public UserNewsFeed getNewsFeed() {
		return newsFeed;
	}

	public void post( String msg ){
		posts.add( new Post(this, msg ) );
		notifyObservers();
	}
	
	private void notifyObservers(){
		for ( PostObserver ob : postObservers ){
			ob.update();
		}
	}
	
	public List<User> getFollowers(){
		return following;
	}

	@Override
	public String toString() {
		return name;
	}
}
