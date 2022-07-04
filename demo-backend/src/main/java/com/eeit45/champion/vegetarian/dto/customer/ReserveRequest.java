package com.eeit45.champion.vegetarian.dto.customer;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ReserveRequest {

    @NotNull
    private String reserveDate;

    @NotNull
    private Integer count;

    private Date reserveTime;

    @NotNull
    private Integer restaurantId;

    private Integer userId;


    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
