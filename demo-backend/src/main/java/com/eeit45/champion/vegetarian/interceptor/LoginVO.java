package com.eeit45.champion.vegetarian.interceptor;

import com.eeit45.champion.vegetarian.model.User;

import java.io.Serializable;

public class LoginVO implements Serializable {
    private Integer id;
    private String token;
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
