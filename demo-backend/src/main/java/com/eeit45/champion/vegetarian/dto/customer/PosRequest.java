package com.eeit45.champion.vegetarian.dto.customer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PosRequest {

    //創建一個參數對應前端送的資料

    @NotEmpty
    private String validDate;
    @NotNull
    private String expiryDate;

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
