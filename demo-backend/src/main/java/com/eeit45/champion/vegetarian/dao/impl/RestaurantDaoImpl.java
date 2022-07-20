package com.eeit45.champion.vegetarian.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.eeit45.champion.vegetarian.dao.RestaurantDao;
import com.eeit45.champion.vegetarian.dto.RestaurantQueryParams;
import com.eeit45.champion.vegetarian.dto.RestaurantRequest;
import com.eeit45.champion.vegetarian.model.Post;
import com.eeit45.champion.vegetarian.model.PostFavorite;
import com.eeit45.champion.vegetarian.model.Restaurant;
import com.eeit45.champion.vegetarian.model.SaveRestaurant;
import com.eeit45.champion.vegetarian.rowmapper.PostRowMapper;
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
		if(restaurantQueryParams.getRestaurantName() != null) {
			sql = sql + " AND restaurantName LIKE :restaurantName";
			map.put("restaurantName", "%" + restaurantQueryParams.getRestaurantName() + "%");
		}
		if(restaurantQueryParams.getRestaurantAddress() != null) {
			sql = sql + " AND restaurantAddress LIKE :restaurantAddress";
			map.put("restaurantAddress", "%" + restaurantQueryParams.getRestaurantAddress() + "%");
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
		if(restaurantQueryParams.getRestaurantName() != null) {
			sql = sql + " AND restaurantName LIKE :restaurantName";
			map.put("restaurantName", "%" + restaurantQueryParams.getRestaurantName() + "%");
		}
		if(restaurantQueryParams.getRestaurantAddress() != null) {
			sql = sql + " AND restaurantAddress LIKE :restaurantAddress";
			map.put("restaurantAddress", "%" + restaurantQueryParams.getRestaurantAddress() + "%");
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

	//新增收藏餐廳
	@Override
	public void addSaveRestaurant(Integer pid, Integer uid) {
		String sql = "INSERT INTO saveRestaurant ( userId, restaurantNumber, saveDate )"+
				"VALUES (:userId, :restaurantNumber, :saveDate)";

				Map<String, Object> map = new HashMap<>();
				map.put("userId", uid);
				map.put("restaurantNumber", pid);
				map.put("saveDate",new Date());

				namedParameterJdbcTemplate.update(sql, map);

	}
	
	//取消收藏餐廳
	@Override
	public boolean delSaveRestaurant(Integer pid, Integer uid) {
		String sql = "DELETE FROM saveRestaurant where restaurantNumber = :restaurantNumber AND userId = :userId";

		Map<String, Object> map = new HashMap<>();
		map.put("restaurantNumber", pid);
		map.put("userId", uid);

		
		namedParameterJdbcTemplate.update(sql, map);
		return true;

	}

	// 搜尋收藏餐廳
	@Override
	public List<Restaurant> findSaveRestaurant(Integer uid) {
		String sql = "SELECT * FROM restaurant LEFT JOIN saveRestaurant ON restaurant.restaurantNumber = saveRestaurant.restaurantNumber where saveRestaurant.userId = :userId ";
		Map<String, Object> map = new HashMap<>();
		map.put("userId", uid);
		
		List<Restaurant> restaurantList = namedParameterJdbcTemplate.query(sql,map ,new RestaurantRowMapper());
		return restaurantList;
	}

	//判斷用戶是否已收藏餐廳
	@Override
	public SaveRestaurant findBySave(Integer pid, Integer uid) {
		String sql = "SELECT * FROM saveRestaurant where restaurantNumber = :restaurantNumber and userId = :userId ";
		Map<String, Object> map = new HashMap<>();
		map.put("restaurantNumber", pid);
		map.put("userId", uid);

		SaveRestaurant saveRestaurant;
		try {
			saveRestaurant = namedParameterJdbcTemplate.queryForObject(sql,map ,new BeanPropertyRowMapper<SaveRestaurant>(SaveRestaurant.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return saveRestaurant;
	}

	
	
}
