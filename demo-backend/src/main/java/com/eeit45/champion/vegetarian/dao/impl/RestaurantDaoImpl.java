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
import com.eeit45.champion.vegetarian.dto.RestaurantQueryParams;
import com.eeit45.champion.vegetarian.dto.RestaurantRequest;
import com.eeit45.champion.vegetarian.model.Restaurant;
import com.eeit45.champion.vegetarian.rowmapper.RestaurantRowMapper;

@Component
public class RestaurantDaoImpl implements RestaurantDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Integer countRestaurant(RestaurantQueryParams restaurantQueryParams) {
		String sql = "SELECT count(*) FROM restaurant WHERE 1=1";
		
		Map<String, Object> map = new HashMap<>();
		
		//查詢條件,判斷前端是否有傳這些參數過來,有的話才加上sql指令
		if(restaurantQueryParams.getRestaurantCategory() != null) {
			sql = sql + " AND restaurantCategory = :restaurantCategory";
			map.put("restaurantCategory", restaurantQueryParams.getRestaurantCategory().name());
		}
		if(restaurantQueryParams.getRestaurantType() != null) {
			sql = sql + " AND restaurantType = :restaurantType";
			map.put("restaurantType", restaurantQueryParams.getRestaurantType().name());
		}
		if(restaurantQueryParams.getSearchName() != null) {
			sql = sql + " AND restaurantName LIKE :searchName";
			map.put("searchName", "%" + restaurantQueryParams.getSearchName() + "%");
		}
		if(restaurantQueryParams.getSearchAddress() != null) {
			sql = sql + " AND restaurantAddress LIKE :searchAddress";
			map.put("searchAddress", "%" + restaurantQueryParams.getSearchAddress() + "%");
		}
		
		Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
		return total;
	}

	@Override
	public List<Restaurant> getRestaurants(RestaurantQueryParams restaurantQueryParams) {
		String sql = "SELECT * FROM restaurant WHERE 1=1";
		Map<String, Object> map = new HashMap<>();
		
		//查詢條件,判斷前端是否有傳這些參數過來,有的話才加上sql指令
		if(restaurantQueryParams.getRestaurantCategory() != null) {
			sql = sql + " AND restaurantCategory = :restaurantCategory";
			map.put("restaurantCategory", restaurantQueryParams.getRestaurantCategory().name());
		}
		if(restaurantQueryParams.getRestaurantType() != null) {
			sql = sql + " AND restaurantType = :restaurantType";
			map.put("restaurantType", restaurantQueryParams.getRestaurantType().name());
		}
		if(restaurantQueryParams.getSearchName() != null) {
			sql = sql + " AND restaurantName LIKE :searchName";
			map.put("searchName", "%" + restaurantQueryParams.getSearchName() + "%");
		}
		if(restaurantQueryParams.getSearchAddress() != null) {
			sql = sql + " AND restaurantAddress LIKE :searchAddress";
			map.put("searchAddress", "%" + restaurantQueryParams.getSearchAddress() + "%");
		}
		
		//排序功能,不需用if來判斷,因為在controller有給預設值
		sql = sql + " ORDER BY " + restaurantQueryParams.getOrderBy() + " " + restaurantQueryParams.getSort();
		
		//分頁功能,不需用if來判斷,因為在controller有給預設值
		sql = sql + " LIMIT :limit OFFSET :offset ";
		map.put("limit", restaurantQueryParams.getLimit());
		map.put("offset", restaurantQueryParams.getOffset());
		
		List<Restaurant> restaurantList = namedParameterJdbcTemplate.query(sql, map, new RestaurantRowMapper());
		return restaurantList;
	}
	

	@Override
	public List<Restaurant> getAllRestaurants() {
		String sql = "SELECT * FROM restaurant";
		Map<String, Object> map = new HashMap<>();
		List<Restaurant> restaurantList = namedParameterJdbcTemplate.query(sql, map,new RestaurantRowMapper());
		if (restaurantList.size()>0) {
			return restaurantList;
		}else {
			return null;
		}
	}
	
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
		map.put("restaurantCategory", restaurantRequest.getRestaurantCategory().toString());
		map.put("restaurantType", restaurantRequest.getRestaurantType().toString());
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
		map.put("restaurantCategory", restaurantRequest.getRestaurantCategory().toString());
		map.put("restaurantType", restaurantRequest.getRestaurantType().toString());
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
