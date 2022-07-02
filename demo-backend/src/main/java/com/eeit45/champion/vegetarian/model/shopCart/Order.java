package com.eeit45.champion.vegetarian.model.shopCart;

import java.util.Date;
import java.util.List;

public class Order {
    private Integer orderId;

    private String orderUUID;
    private Integer userId;
    private String shipping;
    private String payment;
    private String status;

    private Date createdTime;
    private Date updateTime;

    public Order() {
    }

    public Order(Integer orderId, String orderUUID, Integer userId, String shipping, String payment, String status, Date createdTime, Date updateTime) {
        this.orderId = orderId;
        this.orderUUID = orderUUID;
        this.userId = userId;
        this.shipping = shipping;
        this.payment = payment;
        this.status = status;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
    }

    public String getOrderUUID() {
        return orderUUID;
    }

    public void setOrderUUID(String orderUUID) {
        this.orderUUID = orderUUID;
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
        return shipping;
    }

    public void setShipping(String shipping) {
        shipping = shipping;
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
}
