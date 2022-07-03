package com.eeit45.champion.vegetarian.dto.customer;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class BusinessRegisterRequest {
    @NotBlank
    @Email
    private String loginEmail;

    @NotBlank
    private String password;

    private String businessName;
    private String status;
    private String businessPic;
    private Date createdTime;
    private Date lastLoginTime;

    public BusinessRegisterRequest() {
    }

    public BusinessRegisterRequest(String loginEmail, String password, String businessName, String status, String businessPic, Date createdTime, Date lastLoginTime) {
        this.loginEmail = loginEmail;
        this.password = password;
        this.businessName = businessName;
        this.status = status;
        this.businessPic = businessPic;
        this.createdTime = createdTime;
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
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
