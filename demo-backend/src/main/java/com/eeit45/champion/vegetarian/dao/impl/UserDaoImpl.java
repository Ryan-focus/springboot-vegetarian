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

import com.eeit45.champion.vegetarian.dao.UserDao;
import com.eeit45.champion.vegetarian.dto.UserQueryParams;
import com.eeit45.champion.vegetarian.dto.UserRequest;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.rowmapper.UserRowMapper;

@Component
public class UserDaoImpl implements UserDao {
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Integer totalUser(UserQueryParams userQueryParams) {
		
		String sql = "SELECT count(*) FROM vegandb.user WHERE 1=1";
		
		Map<String, Object> map = new HashMap<>();

        sql = filteringSQL(sql, map, userQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;
	}

	@Override
	public List<User> getUsers(UserQueryParams userQueryParams) {
		String sql = "SELECT * FROM vegandb.user WHERE 1=1";
		
		Map<String, Object> map = new HashMap<>();

        sql = filteringSQL(sql, map, userQueryParams);

        // 排序
//        sql = sql + userQueryParams.getSorting();

        //分頁
//        SQL SERVER分頁語法
//        sql = sql + " OFFSET :limit ROWS FETCH NEXT :offset ROWS ONLY";
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", userQueryParams.getLimit());
        map.put("offset", userQueryParams.getOffset());

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        return userList;
		
	}

	@Override
	public List<User> getAllUser() {
		String sql = "SELECT * FROM vegandb.user";
		
		List<User> userList = namedParameterJdbcTemplate.query(sql,new UserRowMapper());
        if (userList!=null) {
            return userList;
        } else {
            return null;
        }
	}

	@Override
	public User getUserById(Integer userId) {
		String sql = "SELECT * FROM vegandb.user WHERE userId = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
	}

	@Override
	public Integer createUser(UserRequest userRequest) {
		String sql = "INSERT INTO vegandb.user ( email, password, userName, status, userPic, registerTime , lastLoginTime)" +
                "VALUES (:email, :password, :userName, :status, :userPic, :registerTime , :lastLoginTime)";

        Map<String, Object> map = new HashMap<>();
        map.put("email", userRequest.getEmail());
        map.put("password", userRequest.getPassword());
        map.put("userName", userRequest.getUserName());
        map.put("status", userRequest.getStatus());
        map.put("userPic", userRequest.getUserPic());
        
        //日期處理
        map.put("registerTime", new Date(System.currentTimeMillis()));
        map.put("lastLoginTime", new Timestamp(System.currentTimeMillis()));

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int userId = keyHolder.getKey().intValue();

        return userId;
	}

	@Override
	public int updateUser(Integer userId, UserRequest userRequest) {
		String sql = "UPDATE vegandb.user SET email = :email, password = :password," +
                " userName = :userName,status= :status,userPic = :userPic WHERE userId = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        map.put("email", userRequest.getEmail());
        map.put("password", userRequest.getPassword());
        map.put("userName", userRequest.getUserName());
        map.put("status", userRequest.getStatus());
        map.put("userPic", userRequest.getUserPic());

        return namedParameterJdbcTemplate.update(sql, map);
	}

	@Override
	public void deleteUserById(Integer userId) {
		
		String sql = "DELETE FROM vegandb.user WHERE userId= :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        namedParameterJdbcTemplate.update(sql, map);
		
	}
	
	private String filteringSQL(String sql, Map<String, Object> map, UserQueryParams userQueryParams) {
		
		if (userQueryParams.getSearch() != null) {
            sql = sql + " AND userid in :search";
            map.put("search", userQueryParams.getSearch());
        }
		return sql;
	}

}