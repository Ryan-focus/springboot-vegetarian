package com.eeit45.champion.vegetarian.dao.impl;

import com.eeit45.champion.vegetarian.dao.UserDao;
import com.eeit45.champion.vegetarian.dto.UserRegisterRequest;
import com.eeit45.champion.vegetarian.model.Product;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.rowmapper.ProductRowMapper;
import com.eeit45.champion.vegetarian.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean login(UserRegisterRequest userRegisterRequest) {

        String sql = "SELECT * FROM users WHERE email = :loginEmail AND password = :password" ;

        Map<String, Object> map = new HashMap<>();
        map.put("loginEmail" , userRegisterRequest.getLoginEmail());
        map.put("password" , userRegisterRequest.getPassword());

        List<User> userList =  namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());

        boolean isPassed = userList.size() > 0 ? true : false ;

        return isPassed;
    }

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO users ( email, password, userName, status, userPic, createdTime , lastLoginTime)" +
                "VALUES (:email, :password, :userName, :status, :userPic, :createdTime , :lastLoginTime)";

        Map<String,Object> map = new HashMap<>();
        map.put("email",userRegisterRequest.getLoginEmail());
        map.put("password",userRegisterRequest.getPassword());
        map.put("userName",userRegisterRequest.getUserName());
        map.put("status",userRegisterRequest.getStatus());
        map.put("userPic",userRegisterRequest.getUserPic());


        //TimeStamp
        Date now = new Date();
        map.put("createdTime",now);
        map.put("lastLoginTime",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);

        //接住MySQL自動生成的ID
        int userId = keyHolder.getKey().intValue();

        return userId;
    }

    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT * FROM users WHERE userId = :userId";

        Map<String,Object> map = new HashMap<>();
        map.put("userId" , userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if(userList.size() > 0){
            return userList.get(0);
        }else{
            return null;
        }
    }


}
