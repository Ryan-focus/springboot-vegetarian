package com.eeit45.champion.vegetarian.dto.customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class BusinessLoginRequest {
    @NotBlank
    @Email
    private String loginEmail;

    @NotBlank
    private String password;

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
}
