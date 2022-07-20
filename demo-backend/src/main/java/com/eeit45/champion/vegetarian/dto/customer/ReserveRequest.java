package com.eeit45.champion.vegetarian.dto.customer;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ReserveRequest {

    @NotBlank
    private String reserveDate;

    @NotNull
    private Integer adult;

    private Integer child;

    private Integer baby;

    private Date reserveTime;

    @NotNull
    private Integer restaurantId;


    @NotBlank
    private String reserveName ;

    @NotBlank
    private String reservePhone;

    @Email
    private String reserveEmail;

    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
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

    public String getReserveName() {
        return reserveName;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public String getReservePhone() {
        return reservePhone;
    }

    public void setReservePhone(String reservePhone) {
        this.reservePhone = reservePhone;
    }

    public String getReserveEmail() {
        return reserveEmail;
    }

    public void setReserveEmail(String reserveEmail) {
        this.reserveEmail = reserveEmail;
    }
}
