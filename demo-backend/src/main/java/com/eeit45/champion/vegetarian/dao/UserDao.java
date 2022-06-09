package com.eeit45.champion.vegetarian.dao;

import com.eeit45.champion.vegetarian.dto.UserRegisterRequest;
import com.eeit45.champion.vegetarian.model.User;

public interface UserDao {
    boolean login(UserRegisterRequest userRegisterRequest);
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
