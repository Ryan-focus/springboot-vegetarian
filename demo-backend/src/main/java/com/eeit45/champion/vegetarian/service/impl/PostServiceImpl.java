package com.eeit45.champion.vegetarian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit45.champion.vegetarian.dao.PostDao;
import com.eeit45.champion.vegetarian.model.Post;
import com.eeit45.champion.vegetarian.model.PostFavorite;
import com.eeit45.champion.vegetarian.model.PostLike;
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
	public void addFavPost(int pid, int uid) {
		postDao.addFavPost(pid, uid);
	}
	
	@Override
	public void addLikePost(int pid, int uid) {
		postDao.addLikePost(pid, uid);
	}

	public PostFavorite findByFavorite(int pid, int uid) {
		return postDao.findByFavorite(pid, uid);

	}
	
	public PostLike findByLike(int pid, int uid) {
		return postDao.findByLike(pid, uid);
	}

	public boolean isFavorite(int pid, int uid) {
		PostFavorite favorite = postDao.findByFavorite(pid, uid);
		if (favorite != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isLike(int pid, int uid) {
		PostLike plike = postDao.findByLike(pid, uid);
		if (plike != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int findCountByPid(int pid) {
		int count = postDao.findCountByPid(pid);
		return count;
	}

	@Override
	public boolean delFavPost(int pid, int uid) {
		return postDao.delFavPost(pid, uid);
	}
	@Override
	public boolean delLikePost(int pid, int uid) {
		return postDao.delLikePost(pid, uid);
	}

	@Override
	public List<Post> findPostByNoAudit() {
		return postDao.findPostByNoAudit();
	}

	@Override
	public List<Post> findPostByNoPass() {
		return postDao.findPostByNoPass();
	}

	@Override
	public List<Post> findPostByCategory1() {
		return postDao.findPostByCategory1();
	}

	@Override
	public List<Post> findPostByCategory2() {
		return postDao.findPostByCategory2();
	}

	@Override
	public List<Post> findPostByCategory3() {
		return postDao.findPostByCategory3();
	}

	@Override
	public List<Post> findPostByCategory4() {
		return postDao.findPostByCategory4();
	}

	@Override
	public List<Post> findPostByCategory5() {
		return postDao.findPostByCategory5();
	}

	

}
