package com.eeit45.champion.vegetarian.model.customer;

import java.util.Date;

public class Reserve {

    private Integer reserveId;

    private Date reserveDateTime;

    private Integer adult;
    private Integer child;
    private Integer baby;

    private Date reserveTime;

    private Integer restaurantId;

    private Integer businessId;

    private Integer userId;

    public Integer getReserveId() {
        return reserveId;
    }

    public void setReserveId(Integer reserveId) {
        this.reserveId = reserveId;
    }

    public Date getReserveDateTime() {
        return reserveDateTime;
    }

    public void setReserveDateTime(Date reserveDateTime) {
        this.reserveDateTime = reserveDateTime;
    }

    public Integer getAdult() {
        return adult;
    }

    public void setAdult(Integer adult) {
        this.adult = adult;
    }

    public Integer getChild() {
        return child;
    }

    public void setChild(Integer child) {
        this.child = child;
    }

    public Integer getBaby() {
        return baby;
    }

    public void setBaby(Integer baby) {
        this.baby = baby;
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

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
