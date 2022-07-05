package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.interceptor.LoginVO;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.model.customer.Business;
import com.eeit45.champion.vegetarian.service.UserService;
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
public class LoginController {

    @Autowired
    BusinessService businessService;

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<Result<LoginVO>> loginValid(@RequestBody @Valid LoginRequest loginRequest){
        LoginVO loginVO = new LoginVO();


        //先判定user 是否存在 ， 只要不為null 即返回 user 數據 以及 Token
        User user  = userService.login(loginRequest) ;

        if(user != null ) {
            loginVO.setId(user.getUserId());
            loginVO.setToken(UUID.randomUUID().toString());
            loginVO.setUser(user);
            Result result = new Result(200,"",loginVO);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }

        Business business = businessService.login(loginRequest);

        if (business != null ) {
            loginVO.setId(business.getBusinessId());
            loginVO.setToken(UUID.randomUUID().toString());
            loginVO.setBusiness(business);
            Result result = new Result(200,"",loginVO);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
