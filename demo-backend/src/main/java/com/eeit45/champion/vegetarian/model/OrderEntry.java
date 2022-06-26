package com.eeit45.champion.vegetarian.model;

public class OrderEntry {

    private int orderEntryId;
    private Integer orderId;
    private int productId;
    private int quantity;
    private int orderEntryPrice;


    public OrderEntry() {
    }

    public OrderEntry(Integer orderId, int productId, int quantity, int orderEntryPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderEntryPrice = orderEntryPrice;
    }

    public OrderEntry(int orderEntryId, Integer orderId, int productId, int quantity, int orderEntryPrice) {
        this.orderEntryId = orderEntryId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderEntryPrice = orderEntryPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getOrderEntryId() {
        return orderEntryId;
    }

    public void setOrderEntryId(int orderEntryId) {
        this.orderEntryId = orderEntryId;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderEntryPrice() {
        return orderEntryPrice;
    }

    public void setOrderEntryPrice(int orderEntryPrice) {
        this.orderEntryPrice = orderEntryPrice;
    }
}
