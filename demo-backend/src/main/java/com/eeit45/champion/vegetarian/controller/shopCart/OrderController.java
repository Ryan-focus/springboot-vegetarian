package com.eeit45.champion.vegetarian.controller.shopCart;

import com.eeit45.champion.vegetarian.dto.shopCart.CreateOrderRequest;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderQueryParams;
import com.eeit45.champion.vegetarian.model.shopCart.Order;
import com.eeit45.champion.vegetarian.service.shopCart.OrderService;
import com.eeit45.champion.vegetarian.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);

        //取得OrderList
        List<Order> orderList = orderService.getOrders(orderQueryParams);

        //取得Order 總數
        Integer count = orderService.countOrder(orderQueryParams);

        //分頁
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    //表示前端送一個已經存在的User所購買的訂單
    // JsonObject : {"buyItemList:[{"productId":9,"quantity:1},{"productId":10,"quantity:18}]}
    //表示前端送了一個 購物車的List 內容物是 數量1個的productID=9的東西  + 數量18個的productID=10的東西
    @PostMapping("{userId}/orders")
    public ResponseEntity<?> createOrders(@PathVariable Integer userId,
                                          @RequestBody @Valid CreateOrderRequest createOrderRequest) {


        Integer orderId = orderService.createOrders(userId, createOrderRequest);

        Order order = orderService.getOrdersById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }


}
