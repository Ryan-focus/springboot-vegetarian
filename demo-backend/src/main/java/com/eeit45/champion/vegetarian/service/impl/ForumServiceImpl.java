package com.eeit45.champion.vegetarian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eeit45.champion.vegetarian.dao.ForumDao;
import com.eeit45.champion.vegetarian.dto.shopCart.ProductQueryParams;
import com.eeit45.champion.vegetarian.model.Forum;
import com.eeit45.champion.vegetarian.service.ForumService;

@Component
public class ForumServiceImpl implements ForumService {

	@Autowired
	private ForumDao forumDao;
	

	
	@Override
	public List<Forum> getForums(Forum forum) {	
		return forumDao.getForums(forumDao);
	}

	@Override
	public Forum getForumById(Integer forumId) {
		return forumDao.getForumById(forumId);
	}

	@Override
	public Integer createForum(Forum forum) {
		return forumDao.createForum(forum);
	}
	
	@Override
	public void updateForum(Integer forumId, Forum forum) {
		forumDao.updateForum(forumId, forum);
		
	}

	@Override
	public void deleteForumById(Integer forumId) {
		forumDao.deleteForumById(forumId);
	}

	



}
