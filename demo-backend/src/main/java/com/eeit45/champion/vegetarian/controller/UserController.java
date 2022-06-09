package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.dto.UserRegisterRequest;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.service.UserService;
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
public class UserController {

    @Autowired
    UserService userService;
//
//    @PostMapping("/api/login")
//    @CrossOrigin //跨域
//    public ResponseEntity<Response<User>> loginValid(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
//
//        Boolean isPassed = userService.login(userRegisterRequest);
//
////        User user = userService.getUserById(userId);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
//    }


    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
