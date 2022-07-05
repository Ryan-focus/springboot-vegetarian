package com.eeit45.champion.vegetarian.dao;

import java.util.List;

import com.eeit45.champion.vegetarian.model.Post;
import com.eeit45.champion.vegetarian.model.PostFavorite;



public interface PostDao {

	boolean addPostImage(Post post);
	
	boolean deletePost(int id);
	
	boolean updatePost(Post post);
	
	Post findPost(int id);
	
	List<Post> findallPost();
	
	Post updateCondition(Post post);
	
	List<Post> findPostByStatus() ;
	
	List<Post> findPostByNoAudit();
	
	List<Post> findPostByNoPass();
	
	void addFavPost(int pid, int uid);
	
	PostFavorite findByFavorite(int pid , int uid);

	
}
