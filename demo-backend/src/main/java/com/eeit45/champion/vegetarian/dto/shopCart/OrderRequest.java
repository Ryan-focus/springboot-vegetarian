package com.eeit45.champion.vegetarian.dto.shopCart;

public class OrderRequest {

    private Integer payment;
    private String status;


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
}
