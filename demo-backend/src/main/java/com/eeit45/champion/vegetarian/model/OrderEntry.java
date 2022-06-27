package com.eeit45.champion.vegetarian.model;

public class OrderEntry {

    private Integer orderEntryId;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Integer orderEntryPrice;


    public OrderEntry() {
    }

    public OrderEntry(Integer orderId, Integer productId, Integer quantity, Integer orderEntryPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderEntryPrice = orderEntryPrice;
    }

    public OrderEntry(Integer orderEntryId, Integer orderId, Integer productId, Integer quantity, Integer orderEntryPrice) {
        this.orderEntryId = orderEntryId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderEntryPrice = orderEntryPrice;
    }

    public Integer getOrderEntryId() {
        return orderEntryId;
    }

    public void setOrderEntryId(Integer orderEntryId) {
        this.orderEntryId = orderEntryId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderEntryPrice() {
        return orderEntryPrice;
    }

    public void setOrderEntryPrice(Integer orderEntryPrice) {
        this.orderEntryPrice = orderEntryPrice;
    }
}
