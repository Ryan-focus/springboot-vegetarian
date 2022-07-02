package com.eeit45.champion.vegetarian.model.shopCart;

public class OrderEntry {

    private Integer orderEntryId;

    private String orderUUID;
    private Integer productId;
    private Integer quantity;
    private Integer orderEntryPrice;


    public OrderEntry() {
    }

    public OrderEntry(String orderUUID, Integer productId, Integer quantity, Integer orderEntryPrice) {
        this.orderUUID = orderUUID;
        this.productId = productId;
        this.quantity = quantity;
        this.orderEntryPrice = orderEntryPrice;
    }

    public OrderEntry(Integer orderEntryId, String orderUUID, Integer productId, Integer quantity, Integer orderEntryPrice) {
        this.orderEntryId = orderEntryId;
        this.orderUUID = orderUUID;
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

    public String getOrderUUID() {
        return orderUUID;
    }

    public void setOrderUUID(String orderUUID) {
        this.orderUUID = orderUUID;
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
