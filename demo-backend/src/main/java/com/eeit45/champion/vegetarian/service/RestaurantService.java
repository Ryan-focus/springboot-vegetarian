package com.eeit45.champion.vegetarian.service;

import java.util.List;

import com.eeit45.champion.vegetarian.dto.RestaurantQueryParams;
import com.eeit45.champion.vegetarian.dto.RestaurantRequest;
import com.eeit45.champion.vegetarian.model.Restaurant;

public interface RestaurantService {
	
	Integer countRestaurant (RestaurantQueryParams restaurantQueryParams);
	
	List<Restaurant> getAllRestaurants();
	List<Restaurant> getRestaurants(RestaurantQueryParams restaurantQueryParams);
	
	Restaurant getRestaurantByNumber(Integer restaurantNumber);
	Integer createRestaurant(RestaurantRequest restaurantRequest);
	void updateRestaurant(Integer restaurantNumber,RestaurantRequest restaurantRequest);
	void deleteRestaurantByNumber(Integer restaurantNumber);
	
	void addSaveRestaurant(Integer pid, Integer uid);
	boolean delSaveRestaurant(Integer pid, Integer uid);
	boolean saveOrNot(Integer pid, Integer uid);
	List<Restaurant> findSaveRestaurant(Integer uid);
}
