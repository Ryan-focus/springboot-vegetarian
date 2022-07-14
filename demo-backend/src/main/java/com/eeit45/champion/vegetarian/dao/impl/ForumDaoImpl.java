package com.eeit45.champion.vegetarian.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.eeit45.champion.vegetarian.dao.ForumDao;
import com.eeit45.champion.vegetarian.model.Forum;
import com.eeit45.champion.vegetarian.rowmapper.ForumRowMapper;

@Component
public class ForumDaoImpl implements ForumDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Integer totalForum(Forum forum) {
		String sql = "SELECT count(*) FROM forum WHERE 1=1 ORDER BY forumUpdateTime DESC ";
		
		Map<String, Object> map = new HashMap<>();
		Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
		return total;
	}

	@Override
	public List<Forum> getForums(ForumDao forumDao) {
		String sql = "SELECT * FROM forum WHERE 1=1";

		Map<String, Object> map = new HashMap<>();
		map.put(sql, forumDao);

		List<Forum> forumList = namedParameterJdbcTemplate.query(sql, map, new ForumRowMapper());

		return forumList;
	}

	@Override
	public Forum getForumById(Integer forumId) {
		String sql = "SELECT * FROM forum WHERE forumId = :forumId";

		Map<String, Object> map = new HashMap<>();
		map.put("forumId", forumId);
		List<Forum> forumList = namedParameterJdbcTemplate.query(sql, map, new ForumRowMapper());
		if (forumList.size() > 0) {
			return forumList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Integer createForum(Forum forum) {
		String sql = "INSERT INTO forum (forumTitle, forumContent, forumCategory,forumImage,forumCreateTime, forumUpdateTime)"
				+ "VALUES (:forumTitle, :forumContent, :forumCategory, :forumImage,:forumCreateTime, :forumUpdateTime)";
		Map<String, Object> map = new HashMap<>();
		map.put("forumTitle", forum.getForumTitle());
		map.put("forumContent", forum.getForumContent());
		map.put("forumCategory", forum.getForumCategory().toString());
		map.put("forumImage", forum.getForumImage());

		Date nowTime = new Date();
		Timestamp timestamp = new Timestamp(nowTime.getTime());

		map.put("forumCreateTime", timestamp);
		map.put("forumUpdateTime", timestamp);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

		int forumId = keyHolder.getKey().intValue();

		return forumId;
	}

	@Override
	public void updateForum(Integer forumId, Forum forum) {
		String sql = "UPDATE forum SET forumTitle = :forumTitle, forumContent = :forumContent, forumCategory = :forumCategory,"
				+ "  forumImage = :forumImage, forumUpdateTime = :forumUpdateTime WHERE forumId = :forumId";

		Map<String, Object> map = new HashMap<>();
		map.put("forumId", forumId);
		map.put("forumTitle", forum.getForumTitle());
		map.put("forumContent", forum.getForumContent());
		map.put("forumCategory", forum.getForumCategory().toString());
		map.put("forumImage", forum.getForumImage());

		Date now = new Date();
		Timestamp timestamp = new Timestamp(now.getTime());

		map.put("forumUpdateTime", timestamp);
		namedParameterJdbcTemplate.update(sql, map);

	}

	@Override
	public void deleteForumById(Integer forumId) {
		String sql = "DELETE FROM forum WHERE forumId= :forumId";

		Map<String, Object> map = new HashMap<>();
		map.put("forumId", forumId);
		namedParameterJdbcTemplate.update(sql, map);

	}

}
