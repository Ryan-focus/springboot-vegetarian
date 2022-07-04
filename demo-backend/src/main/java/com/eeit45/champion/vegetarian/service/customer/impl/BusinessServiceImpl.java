package com.eeit45.champion.vegetarian.service.customer.impl;

import com.eeit45.champion.vegetarian.dao.customer.BusinessDao;
import com.eeit45.champion.vegetarian.dto.customer.BusinessLoginRequest;
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


@Component
public class BusinessServiceImpl implements BusinessService {

    private final static Logger log = LoggerFactory.getLogger(BusinessServiceImpl.class);

    @Autowired
    private BusinessDao businessDao;

    @Override
    public Business login(BusinessLoginRequest businessLoginRequest) {
        Business business = businessDao.getBusinessByEmail(businessLoginRequest.getLoginEmail());

        // checking user exists or not
        if(business == null) {
            log.warn("該 Email:{} 尚未註冊", businessLoginRequest.getLoginEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // using MD5 building hash Value
        String hashPassword = DigestUtils.md5DigestAsHex(businessLoginRequest.getPassword().getBytes());

        //comparing password
        if(business.getPassword().equals(hashPassword)){
            return business;
        } else{
            log.warn("E-mail :{}的密碼不正確", businessLoginRequest.getLoginEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Integer register(BusinessRegisterRequest businessRegisterRequest) {
        // Checking Email exists or not
        Business business =  businessDao.getBusinessByEmail(businessRegisterRequest.getLoginEmail());
        if (business != null){
            //set error comment log in console
            log.warn("該 E-mail :{} 已經被註冊了 ! ", businessRegisterRequest.getLoginEmail());
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
}
