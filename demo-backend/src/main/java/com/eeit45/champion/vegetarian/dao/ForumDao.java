package com.eeit45.champion.vegetarian.dao;

import java.util.List;

import com.eeit45.champion.vegetarian.model.Forum;

public interface ForumDao {
	
	List<Forum> getForums(ForumDao forumDao);
	Forum getForumById(Integer forumId);
	
	Forum getForumByTitle(String forumTitle);
	
	Forum getForumByCategory(Integer forumCategoryId);
	
	
	

}
