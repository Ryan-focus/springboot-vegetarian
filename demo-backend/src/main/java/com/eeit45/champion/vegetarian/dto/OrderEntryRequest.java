package com.eeit45.champion.vegetarian.dto;

public class OrderEntryRequest {


    private String orderId;
    private Integer productId;
    private Integer quantity;
    private Integer orderEntryPrice;

    public OrderEntryRequest() {
    }

    public OrderEntryRequest(String orderId, Integer productId, Integer quantity, Integer orderEntryPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderEntryPrice = orderEntryPrice;
    }

    public OrderEntryRequest(Integer orderEntryId, String orderId, Integer productId, Integer quantity, Integer orderEntryPrice) {

        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderEntryPrice = orderEntryPrice;
    }


    public String getOrderId() {
        return orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getOrderEntryPrice() {
        return orderEntryPrice;
    }
}
