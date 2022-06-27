package com.eeit45.champion.vegetarian.dao;

import java.util.List;

import com.eeit45.champion.vegetarian.model.Post;



public interface PostDao {

	boolean addPostImage(Post post);
	
	boolean deletePost(int id);
	
	public boolean updatePost(Post post);
	
	public Post findPost(int id);
	
	public List<Post> findallPost();
	
	public boolean updateCondition(Post post);
	
	public List<Post> findPostByStatus() ;
	
}
