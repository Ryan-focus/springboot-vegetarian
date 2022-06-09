package com.eeit45.champion.vegetarian.dao;

import com.eeit45.champion.vegetarian.dto.LoginRequest;

public interface LoginDao {
    boolean login(LoginRequest loginRequest);
}
