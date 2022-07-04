package com.eeit45.champion.vegetarian.interceptor;

import com.eeit45.champion.vegetarian.model.customer.Business;

import java.io.Serializable;

public class LoginVO implements Serializable {
    private Integer id;
    private String token;
    private Business business;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Business getUser() {
        return business;
    }

    public void setUser(Business business) {
        this.business = business;
    }
}
