package com.eeit45.champion.vegetarian.dao.impl;

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

import com.eeit45.champion.vegetarian.dao.RestaurantDao;
import com.eeit45.champion.vegetarian.dto.RestaurantRequest;
import com.eeit45.champion.vegetarian.model.Restaurant;
import com.eeit45.champion.vegetarian.rowmapper.RestaurantRowMapper;

@Component
public class RestaurantDaoImpl implements RestaurantDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Restaurant getRestaurantByNumber(Integer restaurantNumber) {
		
		String sql = "SELECT * FROM restaurant WHERE restaurantNumber = :restaurantNumber";
		Map<String, Object> map = new HashMap<>();
		map.put("restaurantNumber", restaurantNumber);
		
		List<Restaurant> restaurantList = namedParameterJdbcTemplate.query(sql, map,new RestaurantRowMapper());
		if (restaurantList.size()>0) {
			return restaurantList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Integer createRestaurant(RestaurantRequest restaurantRequest) {
		String sql = "INSERT INTO restaurant(restaurantName,restaurantTel,restaurantAddress,restaurantCategory,restaurantType,restaurantBusinessHours,restaurantScore,imageUrl,createdTime,updatedTime) "
				+ "VALUES (:restaurantName, :restaurantTel, :restaurantAddress, :restaurantCategory, :restaurantType, :restaurantBusinessHours, :restaurantScore, :imageUrl, :createdTime, :updatedTime)";
		
		Map<String, Object> map = new HashMap<>();
		map.put("restaurantName", restaurantRequest.getRestaurantName());
		map.put("restaurantTel", restaurantRequest.getRestaurantTel());
		map.put("restaurantAddress", restaurantRequest.getRestaurantAddress());
		map.put("restaurantCategory", restaurantRequest.getRestaurantCategory());
		map.put("restaurantType", restaurantRequest.getRestaurantType());
		map.put("restaurantBusinessHours", restaurantRequest.getRestaurantBusinessHours());
		map.put("restaurantScore", restaurantRequest.getRestaurantScore());
		map.put("imageUrl", restaurantRequest.getImageUrl());
		
		Date now = new Date();
		map.put("createdTime", now);
		map.put("updatedTime", now);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);
		int restaurantNumber = keyHolder.getKey().intValue();
		
		return restaurantNumber;
	}

	@Override
	public void updateRestaurant(Integer restaurantNumber, RestaurantRequest restaurantRequest) {
		String sql = "UPDATE restaurant "
				+ "SET restaurantName = :restaurantName ,restaurantTel = :restaurantTel,restaurantAddress = :restaurantAddress,"
				+ "restaurantCategory = :restaurantCategory,restaurantType = :restaurantType,restaurantBusinessHours= :restaurantBusinessHours,"
				+ "restaurantScore = :restaurantScore,imageUrl = :imageUrl,updatedTime = :updatedTime "
				+ " WHERE restaurantNumber = :restaurantNumber";
				
		
		Map<String, Object> map = new HashMap<>();
		map.put("restaurantNumber", restaurantNumber);
		
		map.put("restaurantName", restaurantRequest.getRestaurantName());
		map.put("restaurantTel", restaurantRequest.getRestaurantTel());
		map.put("restaurantAddress", restaurantRequest.getRestaurantAddress());
		map.put("restaurantCategory", restaurantRequest.getRestaurantCategory());
		map.put("restaurantType", restaurantRequest.getRestaurantType());
		map.put("restaurantBusinessHours", restaurantRequest.getRestaurantBusinessHours());
		map.put("restaurantScore", restaurantRequest.getRestaurantScore());
		map.put("imageUrl", restaurantRequest.getImageUrl());
		
		map.put("updatedTime", new Date());
		
		namedParameterJdbcTemplate.update(sql,map);
		
		
	}

	@Override
	public void deleteRestaurantByNumber(Integer restaurantNumber) {
		String sql = "DELETE FROM restaurant WHERE restaurantNumber = :restaurantNumber";
		Map<String, Object> map = new HashMap<>();
		map.put("restaurantNumber", restaurantNumber);
		
		namedParameterJdbcTemplate.update(sql,map);
	}


}
