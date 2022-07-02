package com.eeit45.champion.vegetarian.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eeit45.champion.vegetarian.model.Users;

public class UsersRowMapper implements RowMapper<Users> {
	   @Override
	   public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
	       return new Users(
	               rs.getInt("userid"),
	               rs.getString("email"),
	               rs.getString("password"),
	               rs.getString("userName"),
	               rs.getString("status"),
	               rs.getString("userPic"),
	               rs.getDate("registerTime"),
	               rs.getTimestamp("lastLoginTime")
	       );
	   }
}
