package com.eeit45.champion.vegetarian.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.eeit45.champion.vegetarian.constant.RestaurantCategory;
import com.eeit45.champion.vegetarian.constant.RestaurantType;
import com.eeit45.champion.vegetarian.model.Restaurant;

public class RestaurantRowMapper implements RowMapper<Restaurant>{
	
	@Override
	public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
		Restaurant restaurant = new Restaurant();
		
		restaurant.setRestaurantNumber(rs.getInt("restaurantNumber"));
		restaurant.setRestaurantName(rs.getString("restaurantName"));
		restaurant.setRestaurantTel(rs.getString("restaurantTel"));
		restaurant.setRestaurantAddress(rs.getString("restaurantAddress"));
		
		String categoryStr = rs.getString("restaurantCategory");
		RestaurantCategory restaurantCategory = RestaurantCategory.valueOf(categoryStr);
		restaurant.setRestaurantCategory(restaurantCategory);
		
		String restaurantTypeStr = rs.getString("restaurantType");
		RestaurantType restaurantType = RestaurantType.valueOf(restaurantTypeStr);
		restaurant.setRestaurantType(restaurantType);
		
		
		restaurant.setRestaurantBusinessHours(rs.getString("restaurantBusinessHours"));
		restaurant.setRestaurantScore(rs.getString("restaurantScore"));
		restaurant.setImageUrl(rs.getString("imageUrl"));
		
		//日期處理輸出
        Timestamp createdTime = rs.getTimestamp("createdTime");
        restaurant.setCreatedTime(createdTime);

        Timestamp updatedTime = rs.getTimestamp("updatedTime");
        restaurant.setUpdatedTime(updatedTime);
		
		return restaurant;
	}
	
}
