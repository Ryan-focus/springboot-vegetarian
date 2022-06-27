package com.eeit45.champion.vegetarian.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eeit45.champion.vegetarian.model.Post;



@Service
public interface PostService {
	
boolean addPostImage(Post post);
	
	boolean deletePost(int id);
	
	public boolean updatePost(Post post);
	
	public Post findPost(int id);
	
	public List<Post> findallPost();
	
	public boolean updateCondition(Post post);
	
	public List<Post> findPostByStatus() ;
	

}
