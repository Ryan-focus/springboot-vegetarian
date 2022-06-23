package com.eeit45.champion.vegetarian.model;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private String Shipping;
    private String payment;
    private List<CartEntry> orderEntryList;
    private String status;
    private Date createdTime;
    private Date updatedTime;

    public Order() {
    }

    public Order(int orderId, int userId, String shipping, String payment, List<CartEntry> orderEntryList, String status, Date createdTime, Date updatedTime) {
        this.orderId = orderId;
        this.userId = userId;
        Shipping = shipping;
        this.payment = payment;
        this.orderEntryList = orderEntryList;
        this.status = status;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Order(int userId, String shipping, String payment, List<CartEntry> orderEntryList, String status, Date createdTime, Date updatedTime) {
        this.userId = userId;
        Shipping = shipping;
        this.payment = payment;
        this.orderEntryList = orderEntryList;
        this.status = status;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public List<CartEntry> getOrderEntryList() {
        return orderEntryList;
    }

    public void setOrderEntryList(List<CartEntry> orderEntryList) {
        this.orderEntryList = orderEntryList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
