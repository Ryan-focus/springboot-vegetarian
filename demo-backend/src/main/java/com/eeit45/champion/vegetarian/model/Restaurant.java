package com.eeit45.champion.vegetarian.model;

import java.util.Date;

import com.eeit45.champion.vegetarian.constant.RestaurantCategory;
import com.eeit45.champion.vegetarian.constant.RestaurantType;

public class Restaurant {
	
	private Integer restaurantNumber;
	private String restaurantName;
	private String restaurantTel;
	private String restaurantAddress;
	private RestaurantCategory restaurantCategory;
	private RestaurantType restaurantType;
	private String restaurantBusinessHours;
	private String restaurantScore;
	private String imageUrl;
	private Date createdTime;
    private Date updatedTime;
    
    
	public Integer getRestaurantNumber() {
		return restaurantNumber;
	}
	public void setRestaurantNumber(Integer restaurantNumber) {
		this.restaurantNumber = restaurantNumber;
	}
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
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Override
	public String toString() {
		return "Restaurant [restaurantNumber=" + restaurantNumber + ", restaurantName=" + restaurantName
				+ ", restaurantTel=" + restaurantTel + ", restaurantAddress=" + restaurantAddress
				+ ", restaurantCategory=" + restaurantCategory + ", restaurantType=" + restaurantType
				+ ", restaurantBusinessHours=" + restaurantBusinessHours + ", restaurantScore=" + restaurantScore
				+ ", imageUrl=" + imageUrl + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}

}
