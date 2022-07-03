package com.eeit45.champion.vegetarian.controller.customer;

import com.eeit45.champion.vegetarian.dto.customer.BusinessLoginRequest;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRegisterRequest;
import com.eeit45.champion.vegetarian.interceptor.LoginVO;
import com.eeit45.champion.vegetarian.model.customer.Business;
import com.eeit45.champion.vegetarian.service.customer.BusinessService;
import com.eeit45.champion.vegetarian.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @PostMapping("/users/login")
    public ResponseEntity<Result<Business>> loginValid(@RequestBody @Valid BusinessLoginRequest businessLoginRequest){
        Business business = businessService.login(businessLoginRequest);

        LoginVO loginVO = new LoginVO();
        loginVO.setId(business.getUserId());
        loginVO.setToken(UUID.randomUUID().toString());
        loginVO.setUser(business);

        Result result = new Result(200,"",loginVO);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @PostMapping("/business/register")
    public ResponseEntity<Business> register(@RequestBody @Valid BusinessRegisterRequest businessRegisterRequest){
        Integer businessId = businessService.register(businessRegisterRequest);

        Business business = businessService.getBusinessId(businessId);

        return ResponseEntity.status(HttpStatus.CREATED).body(business);
    }

}
