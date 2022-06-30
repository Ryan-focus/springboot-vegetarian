package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.dto.OrderEntryRequest;
import com.eeit45.champion.vegetarian.dto.OrderRequest;
import com.eeit45.champion.vegetarian.model.*;
import com.eeit45.champion.vegetarian.service.CartService;
import com.eeit45.champion.vegetarian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable Integer userId) {
        List<Order> orderList = orderService.getOrderByUserId(userId);
        //確認不為空值
        if (orderList != null && orderList.size() > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderList);
        } else {
            return null;
        }
    }
    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        Integer orderId = orderService.createOrder(orderRequest);
        Order order = orderService.getOrderById(orderId);
        order.setOrderUUID(orderRequest.getOrderUUID());
        //將cartEntry的物品全部輸入進去OrderEntry
        orderService.insertCartEntryIntoOrderEntry(orderRequest.getOrderUUID());


        //寫入完購物車物品後刪除購物車
        cartService.deleteCartById(orderRequest.getOrderUUID());
        //寫入後刪除所有購物車商品
        cartService.deleteCartEntryById(orderRequest.getOrderUUID());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    @PatchMapping("/payment/{orderId}")
    public ResponseEntity<Order> updatePayment(@PathVariable Integer orderId,
                                               @RequestBody OrderRequest orderRequest) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        orderService.updateOrderPayment(orderRequest.getPayment(),orderId);
        Order updatedOrder = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
    }
    @PatchMapping("/shipping/{orderId}")
    public ResponseEntity<Order> updateShipping(@PathVariable Integer orderId,
                                                @RequestBody OrderRequest orderRequest) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        orderService.updateOrderShipping(orderRequest.getShipping(),orderId);
        Order updatedOrder = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
    }



}
