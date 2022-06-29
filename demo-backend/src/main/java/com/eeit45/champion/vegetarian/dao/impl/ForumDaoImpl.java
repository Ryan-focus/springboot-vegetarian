package com.eeit45.champion.vegetarian.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.eeit45.champion.vegetarian.dao.ForumDao;
import com.eeit45.champion.vegetarian.model.Forum;

@Component
public class ForumDaoImpl implements ForumDao {

	 @Autowired
	 private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public List<Forum> getForums(ForumDao forumDao) {
		String sql = "SELECT * FROM forum WHERE 1=1";
		
		return null;
	}

	@Override
	public Forum getForumById(Integer forumId) {
		
		return null;
	}

	@Override
	public Forum getForumByTitle(String forumTitle) {
		
		return null;
	}

	@Override
	public Forum getForumByCategory(Integer forumCategoryId) {
		
		return null;
	}

}
