package com.eeit45.champion.vegetarian.service.customer.impl;

import com.eeit45.champion.vegetarian.dao.customer.BusinessDao;
import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRegisterRequest;
import com.eeit45.champion.vegetarian.model.customer.Business;
import com.eeit45.champion.vegetarian.service.customer.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Component
public class BusinessServiceImpl implements BusinessService {

    private final static Logger log = LoggerFactory.getLogger(BusinessServiceImpl.class);

    @Autowired
    private BusinessDao businessDao;

    @Override
    public Business login(LoginRequest loginRequest) {
        Business business = businessDao.getBusinessByEmail(loginRequest.getAccount());

        // checking user exists or not
        if(business == null) {
            log.warn("該商家Email:{} 尚未註冊", loginRequest.getAccount());
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            return null ;
        }

        // using MD5 building hash Value
        String hashPassword = DigestUtils.md5DigestAsHex(loginRequest.getPassword().getBytes());

        //comparing password
        if(business.getPassword().equals(hashPassword)){
            return business;
        } else{
            log.warn("商家帳號:{}的密碼不正確", loginRequest.getAccount());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //商家註冊
    @Override
    public Integer register(BusinessRegisterRequest businessRegisterRequest) {
        // Checking Email exists or not
        Business business =  businessDao.getBusinessByEmail(businessRegisterRequest.getAccount());
        if (business != null){
            //set error comment log in console
            log.warn("{}此帳號已被註冊", businessRegisterRequest.getAccount());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // MD5 Hash Value
        String hashPassword = DigestUtils.md5DigestAsHex(businessRegisterRequest.getPassword().getBytes());
        businessRegisterRequest.setPassword(hashPassword);


        //creat Account
        return businessDao.createBusiness(businessRegisterRequest);
    }

    @Override
    public Business getBusinessId(Integer businessId) {
        return businessDao.getBusinessById(businessId);
    }

    @Override
    public List<Business> getAllBusiness() {
        return businessDao.getAllBusiness();
    }
}
