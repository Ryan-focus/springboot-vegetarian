package com.eeit45.champion.vegetarian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:payment_conf.xml")
public class VegetarianApplication {

    public static void main(String[] args) {
        SpringApplication.run(VegetarianApplication.class, args);
    }

}
