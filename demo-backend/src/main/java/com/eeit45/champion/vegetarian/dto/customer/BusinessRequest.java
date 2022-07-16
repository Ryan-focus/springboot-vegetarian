package com.eeit45.champion.vegetarian.dto.customer;

public class BusinessRequest {
    //創建一個參數對應前端送的資料
    private Integer restaurantNumber;

    public Integer getRestaurantNumber() {
        return restaurantNumber;
    }

    public void setRestaurantNumber(Integer restaurantNumber) {
        this.restaurantNumber = restaurantNumber;
    }
}
