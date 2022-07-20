package com.eeit45.champion.vegetarian.dao;

import java.util.List;


import com.eeit45.champion.vegetarian.model.Forum;

public interface ForumDao {
	

	
	//查詢全部
	List<Forum> getForums(ForumDao forumDao);
	//查詢By ID
	Forum getForumById(Integer forumId);
	
	Integer createForum(Forum forum);
	
	void updateForum(Integer forumId, Forum forum);
	
	void deleteForumById(Integer forumId);
	
	Integer totalForum (Forum forum);
	
	List<Forum> seachForumCategory1();
	List<Forum> seachForumCategory2();
	List<Forum> seachForumCategory3();
	List<Forum> seachForumCategory4();
	
	
	
	
	
	
	

}
