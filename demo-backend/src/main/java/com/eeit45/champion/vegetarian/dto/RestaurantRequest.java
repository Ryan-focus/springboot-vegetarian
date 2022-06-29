package com.eeit45.champion.vegetarian.dto;

import javax.validation.constraints.NotNull;

import com.eeit45.champion.vegetarian.constant.RestaurantCategory;
import com.eeit45.champion.vegetarian.constant.RestaurantType;

public class RestaurantRequest {

	@NotNull
	private String restaurantName;
	@NotNull
	private String restaurantTel;
	@NotNull
	private String restaurantAddress;
	@NotNull
	private RestaurantCategory restaurantCategory;
	@NotNull
	private RestaurantType restaurantType;
	private String restaurantBusinessHours;
	private String restaurantScore;
	private String imageUrl;
	
	
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantTel() {
		return restaurantTel;
	}
	public void setRestaurantTel(String restaurantTel) {
		this.restaurantTel = restaurantTel;
	}
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public RestaurantCategory getRestaurantCategory() {
		return restaurantCategory;
	}
	public void setRestaurantCategory(RestaurantCategory restaurantCategory) {
		this.restaurantCategory = restaurantCategory;
	}
	public RestaurantType getRestaurantType() {
		return restaurantType;
	}
	public void setRestaurantType(RestaurantType restaurantType) {
		this.restaurantType = restaurantType;
	}
	public String getRestaurantBusinessHours() {
		return restaurantBusinessHours;
	}
	public void setRestaurantBusinessHours(String restaurantBusinessHours) {
		this.restaurantBusinessHours = restaurantBusinessHours;
	}
	public String getRestaurantScore() {
		return restaurantScore;
	}
	public void setRestaurantScore(String restaurantScore) {
		this.restaurantScore = restaurantScore;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
