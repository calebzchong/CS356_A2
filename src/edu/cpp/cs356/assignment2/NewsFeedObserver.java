package edu.cpp.cs356.assignment2;

import java.util.List;

/**
 * 
 *
 */
public class NewsFeedObserver implements Observer{

	private UserPanel panel;
	private List<Post> posts;
	private int current;
	
	public NewsFeedObserver( UserNewsFeed newsFeed, UserPanel panel ){
		this.panel = panel;
		this.posts = newsFeed.getPosts();
		current = posts.size();
	}
	
	@Override
	public void update() {
		for ( ; current < posts.size(); current++){
			panel.addNewsFeedPost(posts.get(current));
		}
	}

}
