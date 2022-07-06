package com.eeit45.champion.vegetarian.model.customer;

public class PosBusiness {
    private Integer posBusinessId;
    private Integer posId;
    private Integer businessId;
    private Integer visitors;
    private Integer turnOver;

    private String businessName;

    public Integer getPosBusinessId() {
        return posBusinessId;
    }

    public void setPosBusinessId(Integer posBusinessId) {
        this.posBusinessId = posBusinessId;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getVisitors() {
        return visitors;
    }

    public void setVisitors(Integer visitors) {
        this.visitors = visitors;
    }

    public Integer getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(Integer turnOver) {
        this.turnOver = turnOver;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
