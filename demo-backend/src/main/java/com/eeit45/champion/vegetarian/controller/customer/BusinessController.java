package com.eeit45.champion.vegetarian.controller.customer;

import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRegisterRequest;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRequest;
import com.eeit45.champion.vegetarian.dto.customer.PosRequest;
import com.eeit45.champion.vegetarian.interceptor.LoginVO;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.model.customer.Business;
import com.eeit45.champion.vegetarian.service.customer.BusinessService;
import com.eeit45.champion.vegetarian.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @GetMapping("/business")
    public ResponseEntity<List<Business>> getAllBusiness(){
        List<Business> businessList = businessService.getAllBusiness();
        if(businessList != null) return ResponseEntity.status(HttpStatus.OK).body(businessList);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<Business> getBusinessById(@PathVariable Integer businessId){
        Business business = businessService.getBusinessId(businessId);
        if(business != null) return ResponseEntity.status(HttpStatus.OK).body(business);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //商家用戶註冊
    @PostMapping("/business/register")
    public ResponseEntity<Business> register(@RequestBody @Valid BusinessRegisterRequest businessRegisterRequest){
        if(businessRegisterRequest.getStatus() == null) businessRegisterRequest.setStatus("未開通");

        Integer businessId = businessService.register(businessRegisterRequest);

        Business business = businessService.getBusinessId(businessId);

        return ResponseEntity.status(HttpStatus.CREATED).body(business);
    }

    //更新餐廳成為商家
    @PutMapping("/business/{businessId}")
    public ResponseEntity<Business> insertRestaurantNumberById(@PathVariable Integer businessId,
                                                               @RequestBody @Valid BusinessRequest businessRequest){
        Business business = businessService.getBusinessId(businessId);
        if(business == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        businessService.updateRestaurantNumber(businessId,businessRequest);

        Business updateBusiness = businessService.getBusinessId(businessId);

        return ResponseEntity.status(HttpStatus.OK).body(updateBusiness);
    }

    //商家使用者忘記密碼 ， 發送信
    @PostMapping("/business/sendMail")
    public ResponseEntity<Business> resetPassword(@RequestBody LoginRequest loginRequest) {

        Business business = businessService.resetPassword(loginRequest);

        if (business != null) {
            return ResponseEntity.status(HttpStatus.OK).body(business);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
