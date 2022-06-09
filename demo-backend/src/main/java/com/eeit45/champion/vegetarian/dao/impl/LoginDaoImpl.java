package com.eeit45.champion.vegetarian.dao.impl;

import com.eeit45.champion.vegetarian.dao.LoginDao;
import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.rowmapper.UserRowMapper;
import com.eeit45.champion.vegetarian.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginDaoImpl implements LoginDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean login(LoginRequest loginRequest) {

        String sql = "SELECT * FROM users WHERE email = :loginEmail AND password = :password" ;

        Map<String, Object> map = new HashMap<>();
        map.put("loginEmail" ,loginRequest.getLoginEmail());
        map.put("password" , loginRequest.getPassword());

        List<User> userList =  namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());

        boolean isPassed = userList.size() > 0 ? true : false ;

        return isPassed;
    }



}
