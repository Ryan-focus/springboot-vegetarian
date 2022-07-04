package com.eeit45.champion.vegetarian.interceptor;

import com.eeit45.champion.vegetarian.service.customer.impl.BusinessServiceImpl;
import com.eeit45.champion.vegetarian.util.HttpContextUtil;
import com.eeit45.champion.vegetarian.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final static Logger log = LoggerFactory.getLogger(BusinessServiceImpl.class);

    // This method run on the requesting send into Controller before.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Getting token from header
        String token = request.getHeader("token");
        //if token isEmpty
        if(StringUtils.isBlank(token)){
            setReturn(response,401,"使用者未登入，請先登入");
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            return false;
        }

        /**
         * In fact ,
         * We can do this :
         * 1. rechecking token decrypting usersInfo to get requester or not.
         * 2. Whether the token has expired or not.
         */
        return true;
    }

    //The method run on after controller handler;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    //返回json格式錯誤資訊
    private static void setReturn(HttpServletResponse response, Integer code, String msg) throws IOException {


        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        //UTF-8編碼
        httpResponse.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        Result result = new Result(code,msg,"");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(code);
        httpResponse.getWriter().print(json);
    }
}
