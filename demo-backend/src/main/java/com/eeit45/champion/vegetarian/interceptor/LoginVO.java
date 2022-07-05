package com.eeit45.champion.vegetarian.interceptor;

import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.model.customer.Business;

import java.io.Serializable;

public class LoginVO implements Serializable {
    private Integer id;
    private String token;

    private User user ;

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

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
