package com.eeit45.champion.vegetarian.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Business {
    private Integer businessId;
    private String email;
    private String password;
    private String businessName;
    private String status;
    private String businessPic;
    private Date createdTime;
    private Date lastLoginTime;

    public Business() {
    }

    public Business(Integer businessId, String email, String password, String businessName, String status, String businessPic, Date createdTime, Date lastLoginTime) {
        this.businessId = businessId;
        this.email = email;
        this.password = password;
        this.businessName = businessName;
        this.status = status;
        this.businessPic = businessPic;
        this.createdTime = createdTime;
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBusinessPic() {
        return businessPic;
    }

    public void setBusinessPic(String businessPic) {
        this.businessPic = businessPic;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
