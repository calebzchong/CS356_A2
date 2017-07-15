package edu.cpp.cs356.assignment2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PositiveVisitor implements Visitor {
	
	private int positiveCount;
	private int totalCount;
	private Set<String> positiveWords;
	
	public PositiveVisitor(){
		String[] words = {
			"good", "great", "excellent", "nice", "cool", "adorable", "amazing",
			"beautiful", "love", "success", "thanks", "smart", "pretty", "cute"
		};
		positiveWords = new HashSet<String>();
		for ( String word : words){
			positiveWords.add(word);
		}
	}
	
	public void visit(User user) {
		List<Post> posts = user.getPosts();
		for( Post p : posts ){
			totalCount++;
			String contents = p.getMsg();
			for ( String word : contents.split(" ") ){
				word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
				if ( positiveWords.contains(word) ){
					positiveCount++;
					break;
				}
			}
		}
	}
	
	public double getPositiveRatio(){
		if ( totalCount == 0 ){
			return 0;
		}
		return  (double)positiveCount / totalCount;
	}
	

	public void visit(UserGroup group){
		//Do nothing
	}
	
	public void resetCount(){
		positiveCount = 0;
		totalCount = 0;
	}

	
}
