package com.eeit45.champion.vegetarian.rowmapper;

import com.eeit45.champion.vegetarian.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserRowMapper implements RowMapper<User> {

    //將資料庫的結果轉換成User Object
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setUserId(rs.getInt("userId"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setUserName(rs.getString("userName"));
        user.setStatus(rs.getString("status"));
        user.setUserPic(rs.getString("userPic"));
        user.setCreatedTime(rs.getTimestamp("createdTime"));
        user.setLastLoginTime(rs.getTimestamp("lastLoginTime"));

        return user;
    }
}
