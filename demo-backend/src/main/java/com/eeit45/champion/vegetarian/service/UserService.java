package com.eeit45.champion.vegetarian.service;

import com.eeit45.champion.vegetarian.dto.UserLoginRequest;
import com.eeit45.champion.vegetarian.dto.UserRegisterRequest;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.util.Result;

public interface UserService {
    User login(UserLoginRequest userLoginRequest);

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
