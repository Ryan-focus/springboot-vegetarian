package com.eeit45.champion.vegetarian.dao.customer.impl;

import com.eeit45.champion.vegetarian.dao.customer.BusinessDao;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRegisterRequest;
import com.eeit45.champion.vegetarian.model.customer.Business;
import com.eeit45.champion.vegetarian.rowmapper.customer.BusinessRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BusinessDaoImpl implements BusinessDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createBusiness(BusinessRegisterRequest businessRegisterRequest) {
        String sql = "INSERT INTO business ( email, password, businessName, status, businessPic, createdTime , lastLoginTime)" +
                "VALUES (:email, :password, :businessName, :status, :businessPic, :createdTime , :lastLoginTime)";

        Map<String,Object> map = new HashMap<>();
        map.put("email", businessRegisterRequest.getLoginEmail());
        map.put("password", businessRegisterRequest.getPassword());
        map.put("businessName", businessRegisterRequest.getBusinessName());
        map.put("businessPic", businessRegisterRequest.getBusinessPic());

        map.put("status","未開通");

        //TimeStamp
        Date now = new Date();
        map.put("createdTime",now);
        map.put("lastLoginTime",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);

        //接住MySQL自動生成的ID
        Integer businessId = keyHolder.getKey().intValue();

        return businessId;
    }

    @Override
    public Business getBusinessById(Integer businessId) {
        String sql = "SELECT * FROM business WHERE businessId = :businessId";

        Map<String,Object> map = new HashMap<>();
        map.put("businessId" , businessId);

        List<Business> businessList = namedParameterJdbcTemplate.query(sql, map, new BusinessRowMapper());
        if(businessList.size() > 0){
            return businessList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Business getBusinessByEmail(String loginEmail) {
        String sql = "SELECT * FROM business WHERE email = :businessEmail";

        Map<String,Object> map = new HashMap<>();
        map.put("businessEmail" , loginEmail);

        List<Business> businessList = namedParameterJdbcTemplate.query(sql, map, new BusinessRowMapper());
        if(businessList.size() > 0){
            return businessList.get(0);
        }else{
            return null;
        }
    }


}
