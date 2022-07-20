package com.eeit45.champion.vegetarian.dao.customer.impl;

import com.eeit45.champion.vegetarian.dao.customer.BusinessDao;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRegisterRequest;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRequest;
import com.eeit45.champion.vegetarian.model.customer.Business;
import com.eeit45.champion.vegetarian.model.customer.Pos;
import com.eeit45.champion.vegetarian.rowmapper.customer.BusinessRowMapper;
import com.eeit45.champion.vegetarian.rowmapper.customer.PosRowMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
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
        String sql = "INSERT INTO business ( email, password, principalName,principalPhone, businessName,located, businessPic, status,updateTime,  createdTime , lastLoginTime)" +
                "VALUES (:email, :password, :principalName, :principalPhone , :businessName, :located,   :businessPic, :status,:updateTime, :createdTime , :lastLoginTime)";

        Map<String,Object> map = new HashMap<>();
        map.put("email", businessRegisterRequest.getAccount());
        map.put("password", businessRegisterRequest.getPassword());
        map.put("principalName", businessRegisterRequest.getPrincipalName());
        map.put("principalPhone", businessRegisterRequest.getPrincipalPhone());
        map.put("businessName", businessRegisterRequest.getBusinessName());
        map.put("located", businessRegisterRequest.getLocated());
        map.put("businessPic", businessRegisterRequest.getBusinessPic());
        map.put("status",businessRegisterRequest.getStatus());

        //TimeStamp
        Date now = new Date();
        map.put("updateTime",now);
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

    @Override
    public void updateStatus(Integer businessId, String status , String UUID) {
        String sql = "UPDATE business SET status = :status ,updateTime = :updateTime , UUID = :UUID " +
                "WHERE businessId = :businessId  " ;
        Map<String , Object > map = new HashMap<>();
        map.put("status" , status);
        map.put("UUID" , UUID);


        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        map.put("updateTime", timestamp);

        map.put("businessId" , businessId);


        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public List<Business> getAllBusiness() {
        String sql = "SELECT * FROM business";

        List<Business> businessList = namedParameterJdbcTemplate.query(sql,new BusinessRowMapper());

        if(businessList != null) return businessList;

        return null;
    }

    @Override
    public void updateRestaurantNumber(Integer businessId, BusinessRequest businessRequest) {
        String sql = "UPDATE business SET restaurantNumber = :restaurantNumber , updateTime = :updateTime " +
                " WHERE businessId = :businessId  " ;


        Map<String , Object > map = new HashMap<>();
        map.put("restaurantNumber",businessRequest.getRestaurantNumber());

        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        map.put("updateTime", timestamp);

        map.put("businessId" , businessId);

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public String resetPassword(String email) {
        String sql = "UPDATE business SET password = :password where email= :email";

        //亂數生成8位數
        String rsu = RandomStringUtils.random(8,true,true);
        Map<String, Object> map = new HashMap<>();

        map.put("password",DigestUtils.md5DigestAsHex(rsu.getBytes()));
        map.put("email", email);

        namedParameterJdbcTemplate.update(sql, map);

        return rsu;
    }
}
