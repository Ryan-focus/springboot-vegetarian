package com.eeit45.champion.vegetarian.service;

import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.util.Response;

public interface LoginService {
    boolean login(LoginRequest loginRequest);
}
