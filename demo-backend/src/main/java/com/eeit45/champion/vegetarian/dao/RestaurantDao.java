package com.eeit45.champion.vegetarian.dao;

import com.eeit45.champion.vegetarian.dto.RestaurantRequest;
import com.eeit45.champion.vegetarian.model.Restaurant;

public interface RestaurantDao {
	
	Restaurant getRestaurantByNumber(Integer restaurantNumber);
	Integer createRestaurant(RestaurantRequest restaurantRequest);
	void updateRestaurant(Integer restaurantNumber,RestaurantRequest restaurantRequest);
	void deleteRestaurantByNumber(Integer restaurantNumber);
	
}
