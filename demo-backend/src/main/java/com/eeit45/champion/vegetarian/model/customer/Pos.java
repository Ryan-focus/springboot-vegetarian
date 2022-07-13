package com.eeit45.champion.vegetarian.model.customer;

import java.util.Date;
import java.util.List;

public class Pos {

    private Integer posId;
    private Integer businessId;
    private String validDate;
    private Date expiryDate;
    private String UUID;

    private List<PosBusiness> posBusinessList;
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

    public List<PosBusiness> getPosBusinessList() {
        return posBusinessList;
    }

    public void setPosBusinessList(List<PosBusiness> posBusinessList) {
        this.posBusinessList = posBusinessList;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
