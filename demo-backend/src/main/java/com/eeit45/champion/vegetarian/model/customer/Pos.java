package com.eeit45.champion.vegetarian.model.customer;

import java.util.Date;

public class Pos {

    private Integer posId;
    private Integer businessId;
    private String validDate;
    private Date expiryDate;
    private Integer visitors;
    private Integer turnOver;
    private Date startDate;
    private Date lastUsingDate;

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

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getLastUsingDate() {
        return lastUsingDate;
    }

    public void setLastUsingDate(Date lastUsingDate) {
        this.lastUsingDate = lastUsingDate;
    }
}
