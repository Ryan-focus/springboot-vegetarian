package com.eeit45.champion.vegetarian.rowmapper.customer;

import com.eeit45.champion.vegetarian.model.customer.Business;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessRowMapper implements RowMapper<Business> {

    //將資料庫的結果轉換成User Object
    @Override
    public Business mapRow(ResultSet rs, int rowNum) throws SQLException {
        Business business = new Business();

        business.setUserId(rs.getInt("userId"));
        business.setEmail(rs.getString("email"));
        business.setPassword(rs.getString("password"));
        business.setUserName(rs.getString("userName"));
        business.setStatus(rs.getString("status"));
        business.setUserPic(rs.getString("userPic"));
        business.setCreatedTime(rs.getTimestamp("createdTime"));
        business.setLastLoginTime(rs.getTimestamp("lastLoginTime"));

        return business;
    }
}
