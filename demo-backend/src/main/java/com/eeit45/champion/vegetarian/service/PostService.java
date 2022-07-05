package com.eeit45.champion.vegetarian.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eeit45.champion.vegetarian.model.Post;
import com.eeit45.champion.vegetarian.model.PostFavorite;



@Service
public interface PostService {
	
boolean addPostImage(Post post);
	
	boolean deletePost(int id);
	
	boolean updatePost(Post post);
	
	Post findPost(int id);
	
	List<Post> findallPost();
	
	Post updateCondition(Post post);
	
	List<Post> findPostByStatus() ;
	
	List<Post> findPostByNoAudit();
	
	List<Post> findPostByNoPass();
	
	void addFavPost(int pid,int uid);
	
	PostFavorite findByFavorite(int pid , int uid);
	
	boolean isFavorite(int pid, int uid);

	

}
