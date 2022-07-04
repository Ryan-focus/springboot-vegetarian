package com.eeit45.champion.vegetarian.rowmapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.eeit45.champion.vegetarian.model.User;

public class UserRowMapper implements RowMapper<User> {
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		
		user.setUserId(rs.getInt("userId"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setUserName(rs.getString("userName"));
		user.setStatus(rs.getString("status"));
		user.setUserPic(rs.getString("userPic"));

		Date registerTime = rs.getDate("registerTime");
		user.setRegisterTime(registerTime);

		Timestamp lastLoginTime = rs.getTimestamp("lastLoginTime");
		user.setLastLoginTime(lastLoginTime);
		
		return user;
	}

}
