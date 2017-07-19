package edu.cpp.cs356.assignment2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User Class.
 * Composite design pattern.
 * Uses observers to notify new posts.
 *
 */
public class User implements TwitterEntity{
	
	private String name;
	private List<User> following;
	private List<PostObserver> postObservers; //Followers
	private List<Post> posts; //My Posts
	private UserNewsFeed newsFeed;
	private Date creationTime;
	
	
	public User( String name ){
		creationTime = new Date();
		this.name = name;
		following = new ArrayList<>();
		postObservers = new ArrayList<>();
		posts = new ArrayList<>();
		newsFeed = new UserNewsFeed();
		postObservers.add( new PostObserver(posts, newsFeed));
	}
	
	public void follow( User user ){
		user.postObservers.add( new PostObserver( user.posts, newsFeed ) );
		following.add(user);
	}
	
	public UserNewsFeed getNewsFeed() {
		return newsFeed;
	}

	public void post( String msg ){
		Post p = new Post(this, msg );
		posts.add( p );
		notifyObservers();
	}
	
	private void notifyObservers(){
		for ( PostObserver ob : postObservers ){
			ob.update();
		}
	}
	
	public List<Post> getPosts(){
		return posts;
	}
	
	public List<User> getFollowers(){
		return following;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void acceptVisitor( Visitor v) {
		v.visit(this);
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
