package com.eeit45.champion.vegetarian.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eeit45.champion.vegetarian.model.Post;
import com.eeit45.champion.vegetarian.model.PostFavorite;



@Service
public interface PostService {
	
boolean addPostImage(Post post);
	
	boolean deletePost(int id);
	
	public boolean updatePost(Post post);
	
	public Post findPost(int id);
	
	public List<Post> findallPost();
	
	public Post updateCondition(Post post);
	
	public List<Post> findPostByStatus() ;
	
	void addFavPost(int pid,int uid);
	
	PostFavorite findByFavorite(int pid , int uid);
	
	public boolean isFavorite(int pid, int uid);
	

}
