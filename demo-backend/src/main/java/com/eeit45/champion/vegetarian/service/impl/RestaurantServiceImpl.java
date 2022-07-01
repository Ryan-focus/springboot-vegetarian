package com.eeit45.champion.vegetarian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eeit45.champion.vegetarian.dao.RestaurantDao;
import com.eeit45.champion.vegetarian.dto.RestaurantQueryParams;
import com.eeit45.champion.vegetarian.dto.RestaurantRequest;
import com.eeit45.champion.vegetarian.model.Restaurant;
import com.eeit45.champion.vegetarian.service.RestaurantService;

@Component
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantDao restaurantDao;
	
	@Override
	public Integer countRestaurant(RestaurantQueryParams restaurantQueryParams) {
		return restaurantDao.countRestaurant(restaurantQueryParams);
	}


	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantDao.getAllRestaurants();
	}
	
	@Override
	public List<Restaurant> getRestaurants(RestaurantQueryParams restaurantQueryParams) {
		return restaurantDao.getRestaurants(restaurantQueryParams);
	}
	
	@Override
	public Restaurant getRestaurantByNumber(Integer restaurantNumber) {
		return restaurantDao.getRestaurantByNumber(restaurantNumber);
	}

	@Override
	public Integer createRestaurant(RestaurantRequest restaurantRequest) {
		return restaurantDao.createRestaurant(restaurantRequest);
	}

	@Override
	public void updateRestaurant(Integer restaurantNumber, RestaurantRequest restaurantRequest) {
		restaurantDao.updateRestaurant(restaurantNumber,restaurantRequest);
		
	}

	@Override
	public void deleteRestaurantByNumber(Integer restaurantNumber) {
		restaurantDao.deleteRestaurantByNumber(restaurantNumber);
	}

}
