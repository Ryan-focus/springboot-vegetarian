package com.eeit45.champion.vegetarian.rowmapper.customer;

import com.eeit45.champion.vegetarian.model.customer.Business;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BusinessRowMapper implements RowMapper<Business> {

    //將資料庫的結果轉換成Business Object
    @Override
    public Business mapRow(ResultSet rs, int rowNum) throws SQLException {
        Business business = new Business();

        business.setBusinessId(rs.getInt("businessId"));
        business.setEmail(rs.getString("email"));
        business.setPassword(rs.getString("password"));
        business.setPrincipalName(rs.getString("principalName"));
        business.setPrincipalPhone(rs.getString("principalPhone"));
        business.setBusinessName(rs.getString("businessName"));
        business.setLocated(rs.getString("located"));
        business.setStatus(rs.getString("status"));
        business.setRestaurantNumber(rs.getInt("restaurantNumber"));
        business.setUUID(rs.getString("UUID"));
        business.setBusinessPic(rs.getString("businessPic"));
        business.setCreatedTime(rs.getTimestamp("createdTime"));
        business.setLastLoginTime(rs.getTimestamp("lastLoginTime"));
        business.setUpdateTime(rs.getTimestamp("updateTime"));

        return business;
    }
}
