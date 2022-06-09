package com.eeit45.champion.vegetarian.rowmapper;

import com.eeit45.champion.vegetarian.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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

        Timestamp createdTime = rs.getTimestamp("createdTime");
        user.setCreatedTime(createdTime);

        Timestamp lastLoginTime = rs.getTimestamp("lastLoginTime");
        user.setLastLoginTime(lastLoginTime);

        return user;
    }
}
