package com.eeit45.champion.vegetarian.util;

import com.eeit45.champion.vegetarian.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration

public class InterceptorWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        WebMvcConfigurer.super.addInterceptors(registry);
        //需經過Interceptor的url
        registry.addInterceptor(loginInterceptor).addPathPatterns("/product/*")
        //放行路徑，可以新增多個
                .excludePathPatterns("/user/*");
    }
}
