package com.eeit45.champion.vegetarian.service.impl;

import com.eeit45.champion.vegetarian.dao.UserDao;
import com.eeit45.champion.vegetarian.dto.UserLoginRequest;
import com.eeit45.champion.vegetarian.dto.UserRegisterRequest;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;



@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getLoginEmail());
        if(user == null) {
            log.warn("該 Email:{} 尚未註冊", userLoginRequest.getLoginEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(user.getPassword().equals(userLoginRequest.getPassword())){
            return user;
        } else{
            log.warn("E-mail :{}的密碼不正確",userLoginRequest.getLoginEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        // Checking Email exists or not
        User user =  userDao.getUserByEmail(userRegisterRequest.getLoginEmail());
        if (user != null){
            //set error comment log in console
            log.warn("該 E-mail :{} 已經被註冊了 ! ", userRegisterRequest.getLoginEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //creat Account
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
