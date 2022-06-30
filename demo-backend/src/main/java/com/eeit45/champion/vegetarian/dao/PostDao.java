package com.eeit45.champion.vegetarian.dao;

import java.util.List;

import com.eeit45.champion.vegetarian.model.Post;
import com.eeit45.champion.vegetarian.model.PostFavorite;



public interface PostDao {

	boolean addPostImage(Post post);
	
	boolean deletePost(int id);
	
	public boolean updatePost(Post post);
	
	public Post findPost(int id);
	
	public List<Post> findallPost();
	
	public Post updateCondition(Post post);
	
	public List<Post> findPostByStatus() ;
	
	void addFavPost(int pid, int uid);
	
	PostFavorite findByFavorite(int pid , int uid);
	
}
