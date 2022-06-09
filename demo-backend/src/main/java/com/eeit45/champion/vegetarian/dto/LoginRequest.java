package com.eeit45.champion.vegetarian.dto;

import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull
    private String loginEmail;

    @NotNull
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
