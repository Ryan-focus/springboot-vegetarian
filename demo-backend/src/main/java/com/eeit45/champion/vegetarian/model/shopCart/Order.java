package com.eeit45.champion.vegetarian.model.shopCart;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Order {
    private Integer orderId;

    private Integer userId;
    private Integer payment;
    private String status;
    private Date createdTime;
    private Date updateTime;

    //設計邏輯 : 在一張訂單裡面，會包含一個OrderItemList ，裡面會包含多個Item
    private List<OrderItem> orderItemList;

    public Order() {
    }

    public Order(Integer orderId, Integer userId, Integer payment, String status, Timestamp createdTime, Timestamp updateTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.payment = payment;
        this.status = status;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
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

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
