package com.eeit45.champion.vegetarian.service;

import com.eeit45.champion.vegetarian.dto.UserRegisterRequest;
import com.eeit45.champion.vegetarian.model.User;

public interface UserService {
    boolean login(UserRegisterRequest userRegisterRequest);

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
