package com.eeit45.champion.vegetarian.dao;

import java.util.List;

import com.eeit45.champion.vegetarian.model.Post;
import com.eeit45.champion.vegetarian.model.PostFavorite;
import com.eeit45.champion.vegetarian.model.PostLike;



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
	
	List<Post> findPostByCategory1();
	List<Post> findPostByCategory2();
	List<Post> findPostByCategory3();
	List<Post> findPostByCategory4();
	List<Post> findPostByCategory5();
	
	void addFavPost(int pid, int uid);
	void addLikePost(int pid, int uid);
	int findCountByPid(int pid);
	boolean delFavPost(int pid, int uid);
	boolean delLikePost(int pid, int uid);
	List<Post> findFavoritePost(int uid);
	List<Post> findPostByUser(int uid);
	List<Post> findPostByUserNoAudit(int uid);
	List<Post> findPostByUserNoPass(int uid);
	List<Post> findPostbyLike();
	
	PostFavorite findByFavorite(int pid , int uid);
	PostLike findByLike(int pid, int uid);
	
}
