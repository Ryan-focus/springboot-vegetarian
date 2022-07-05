package com.eeit45.champion.vegetarian.controller.customer;

import com.eeit45.champion.vegetarian.dto.LoginRequest;
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



    @PostMapping("/business/register")
    public ResponseEntity<Business> register(@RequestBody @Valid BusinessRegisterRequest businessRegisterRequest){
        Integer businessId = businessService.register(businessRegisterRequest);

        Business business = businessService.getBusinessId(businessId);

        return ResponseEntity.status(HttpStatus.CREATED).body(business);
    }

}
