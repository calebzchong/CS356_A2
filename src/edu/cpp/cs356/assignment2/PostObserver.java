package edu.cpp.cs356.assignment2;

import java.util.List;

/**
 * Observer class for user's posts.
 * When notified, it fetches new posts from followed user and adds them
 * to the follower's newsfeed.
 */
public class PostObserver implements Observer{

	private List<Post> posts;
	private int current;
	private UserNewsFeed newsFeed;
	
	public PostObserver(List<Post> posts, UserNewsFeed newsFeed){
		this.posts = posts;
		current = posts.size();
		this.newsFeed = newsFeed;
	}
	
	@Override
	public void update() {
		for ( ; current < posts.size(); current++){
			newsFeed.addPost(posts.get(current));
		}
	}

}
