package com.eeit45.champion.vegetarian.model;

import java.util.Date;

public class SaveRestaurant {
	
	private Integer userId;
	private Integer restaurantNumber;
	private Date saveDate;
	
	public SaveRestaurant() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRestaurantNumber() {
		return restaurantNumber;
	}

	public void setRestaurantNumber(Integer restaurantNumber) {
		this.restaurantNumber = restaurantNumber;
	}

	public Date getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}	
	
}
