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
    @PostMapping("/{cartId}")
    public ResponseEntity<Order> createOrder(@PathVariable Integer cartId,
                                             @RequestBody @Valid OrderRequest orderRequest) {
        Integer orderId = orderService.createOrder(orderRequest);
        Order order = orderService.getOrderById(orderId);
        //取出購物車的所有物品準備寫入order
        List<CartEntry> cartEntryList = cartService.getCartEntriesById(cartId);
        //轉換cartEntry變成Order
        for (int i = 0; i < cartEntryList.size(); i++) {
            OrderEntry orderEntry = orderService.CartEntryToOrderEntry(cartEntryList.get(i));


        }

        //寫入完購物車物品後刪除購物車
        cartService.deleteCartById(cartId);
        //寫入後刪除所有購物車商品
        cartService.deleteCartEntryById(cartId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }


}
