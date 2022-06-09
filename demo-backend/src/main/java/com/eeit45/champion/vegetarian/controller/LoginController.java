package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.service.LoginService;
import com.eeit45.champion.vegetarian.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/api/login")
    @CrossOrigin //跨域
    public ResponseEntity<Response<User>> loginValid(@RequestBody @Valid LoginRequest loginRequest){

        Boolean isPassed = loginService.login(loginRequest);



        return ResponseEntity.status(HttpStatus.OK).body();
    }

}
