package com.eeit45.champion.vegetarian.dto.customer;

import com.eeit45.champion.vegetarian.constant.StatusCategory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PosRequest {

    //創建一個參數對應前端送的資料

    @NotNull
    private StatusCategory validDate;

    private String expiryDate;

    private String UUID;

    public StatusCategory getValidDate() {
        return validDate;
    }

    public void setValidDate(StatusCategory validDate) {
        this.validDate = validDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
