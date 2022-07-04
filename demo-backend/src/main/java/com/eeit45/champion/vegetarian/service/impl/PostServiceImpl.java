package com.eeit45.champion.vegetarian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit45.champion.vegetarian.dao.PostDao;
import com.eeit45.champion.vegetarian.model.Post;
import com.eeit45.champion.vegetarian.model.PostFavorite;
import com.eeit45.champion.vegetarian.service.PostService;


@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;

	public boolean addPostImage(Post post) {
		return postDao.addPostImage(post);

	}

	public boolean deletePost(int id) {
		return postDao.deletePost(id);

	}

	public boolean updatePost(Post post) {
		return postDao.updatePost(post);

	}

	public Post findPost(int id) {
		return postDao.findPost(id);
	}

	public List<Post> findallPost() {
		return postDao.findallPost();

	}
	
	public Post updateCondition(Post post) {
		return postDao.updateCondition(post);
	}
	
	public List<Post> findPostByStatus() {
		return postDao.findPostByStatus();
	}
	
	@Override
	public void addFavPost(int pid,int uid) {
		 postDao.addFavPost(pid, uid);
	}
	
	public PostFavorite findByFavorite(int pid , int uid) {
		return postDao.findByFavorite(pid, uid);
		
	}
	
	public boolean isFavorite(int pid, int uid) {
        PostFavorite favorite = postDao.findByFavorite(pid, uid);
        return favorite != null;//如果对象有值，则为true，反之，则为false
    }

	@Override
	public List<Post> findPostByNoAudit() {
		return postDao.findPostByNoAudit();
	}

	@Override
	public List<Post> findPostByNoPass() {
		return postDao.findPostByNoPass();
	}



}
