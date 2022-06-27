package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.dto.UserLoginRequest;
import com.eeit45.champion.vegetarian.dto.UserRegisterRequest;
import com.eeit45.champion.vegetarian.interceptor.LoginVO;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.service.UserService;
import com.eeit45.champion.vegetarian.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users/login")
    @CrossOrigin(origins = "http://localhost:8080") //跨域
    public ResponseEntity<Result<User>> loginValid(@RequestBody @Valid UserLoginRequest userLoginRequest){
        User user = userService.login(userLoginRequest);

        LoginVO loginVO = new LoginVO();
        loginVO.setId(user.getUserId());
        loginVO.setToken(UUID.randomUUID().toString());
        loginVO.setUser(user);

        Result result = new Result(200,"",loginVO);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
