package com.eeit45.champion.vegetarian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class VegetarianApplication {

    public static void main(String[] args) {
        SpringApplication.run(VegetarianApplication.class, args);
    }

}
