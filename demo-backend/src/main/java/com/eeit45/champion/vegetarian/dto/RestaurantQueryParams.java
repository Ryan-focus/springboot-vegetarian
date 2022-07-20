package com.eeit45.champion.vegetarian.dto;

import com.eeit45.champion.vegetarian.constant.RestaurantCategory;
import com.eeit45.champion.vegetarian.constant.RestaurantType;

public class RestaurantQueryParams {
	private RestaurantCategory restaurantCategory;
	private RestaurantType restaurantType;
	private String restaurantName;
	private String restaurantAddress;
	
	private String orderBy;
	private String sort;
	
	private Integer limit;
	private Integer offset;
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
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
}
