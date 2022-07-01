package com.eeit45.champion.vegetarian.dao.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eeit45.champion.vegetarian.dao.UsersDao;
import com.eeit45.champion.vegetarian.model.Users;
import com.eeit45.champion.vegetarian.rowmapper.UsersRowMapper;

@Repository
public class UsersDaoImpl implements UsersDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UsersDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Users> findAll() {
		String sql = "SELECT * FROM user;";
		   return jdbcTemplate.query(sql,new UsersRowMapper());
	}

	@Override
	public int addUser(Users users) {
		   String sql = "INSERT into user(email,password,userName, status, userPic, registerTime, lastLoginTime)VALUES (?,?,?,?,?,?,?);";
		   users.setRegisterTime(new Date(System.currentTimeMillis()));
		   users.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		   return jdbcTemplate.update(sql, users.getEmail(),
				   users.getPassword(),
				   users.getUserName(),
				   users.getStatus(),
				   users.getUserPic(),
				   users.getRegisterTime(),
				   users.getLastLoginTime());
	}

	@Override
	public Optional<Users> findById(Integer userId) {
		String sql = "SELECT * FROM user WHERE userId = ?";
		   return jdbcTemplate.query(sql,new UsersRowMapper(),userId)
		           .stream()
		           .findFirst();
	}

	@Override
	public int deleteUser(Integer userId) {
		String sql = "DELETE FROM user WHERE userId = ?";
		   return jdbcTemplate.update(sql,userId);
	}

	@Override
	public int updateUser(Integer userId, Users users) {
		String sql = "UPDATE user SET  email = ?, password = ?, userName = ?, status = ?, userPic = ?, registerTime = ?, lastLoginTime = ? WHERE userId = ?";
		   return jdbcTemplate.update(sql,
				   users.getEmail(),
				   users.getPassword(),
				   users.getUserName(),
				   users.getStatus(),
				   users.getUserPic(),
				   users.getRegisterTime(),
				   users.getLastLoginTime(),userId);
	}

}
