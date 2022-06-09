package com.eeit45.champion.vegetarian.service.impl;

import com.eeit45.champion.vegetarian.dao.LoginDao;
import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.service.LoginService;
import com.eeit45.champion.vegetarian.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public boolean login(LoginRequest loginRequest) {
        return loginDao.login(loginRequest);
    }
}
