package com.eeit45.champion.vegetarian.service.shopCart;

import com.eeit45.champion.vegetarian.dto.shopCart.CreateOrderRequest;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderQueryParams;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Order;

import java.util.List;

public interface OrderService {

    Integer createOrders(Integer userId, CreateOrderRequest createOrderRequest);


    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);

    Order getOrdersById(Integer orderId);

    List<Order> getAllOrders();

    void updateOrder(Integer orderId, OrderRequest orderRequest);


}
