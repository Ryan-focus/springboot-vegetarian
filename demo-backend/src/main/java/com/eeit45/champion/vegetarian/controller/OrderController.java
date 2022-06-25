package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.model.Order;
import com.eeit45.champion.vegetarian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable Integer userId){
        List<Order> orderList = orderService.getOrderByUserId(userId);

        if (orderList.size()>0){
            return (ResponseEntity<List<Order>>) orderList;
        }

        return null;
    }

}
