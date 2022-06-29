package com.eeit45.champion.vegetarian.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eeit45.champion.vegetarian.model.Forum;

public class ForumRowMapper implements RowMapper<Forum> {

	@Override
	public Forum mapRow(ResultSet rs, int rowNum) throws SQLException {
		Forum forum = new Forum();
		return null;
	}

}
