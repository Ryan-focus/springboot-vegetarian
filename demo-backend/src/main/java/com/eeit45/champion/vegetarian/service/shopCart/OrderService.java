package com.eeit45.champion.vegetarian.service.shopCart;

import com.eeit45.champion.vegetarian.dto.shopCart.CreateOrderRequest;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Order;

import java.util.List;

public interface OrderService {

    Integer createOrder(OrderRequest orderRequest);

    Integer createOrders(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    Order getOrdersById(Integer orderId);

    public List<Order> getOrderByUserId(Integer userId);

     void insertCartEntryIntoOrderEntry(String cartUUID);

    void updateOrder(Integer orderId, OrderRequest orderRequest);

    void updateOrderPayment(String payment,Integer orderId);

    void updateOrderShipping(String shipping,Integer orderId);

    List<Order> getAllOrder();

    void deleteOrderById(Integer orderId);



}
