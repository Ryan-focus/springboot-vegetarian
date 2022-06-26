package com.eeit45.champion.vegetarian.model;

import java.util.Date;
import java.util.List;

public class Order {
    private Integer orderId;
    private Integer userId;
    private String Shipping;
    private String payment;
    private String status;

    public Order() {
    }

    public Order(Integer orderId, Integer userId, String shipping, String payment, String status) {
        this.orderId = orderId;
        this.userId = userId;
        Shipping = shipping;
        this.payment = payment;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getShipping() {
        return Shipping;
    }

    public void setShipping(String shipping) {
        Shipping = shipping;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
