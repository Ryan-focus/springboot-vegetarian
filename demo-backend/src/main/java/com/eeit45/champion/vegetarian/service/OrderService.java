package com.eeit45.champion.vegetarian.service;

import com.eeit45.champion.vegetarian.dto.OrderEntryRequest;
import com.eeit45.champion.vegetarian.dto.OrderRequest;
import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.model.Order;
import com.eeit45.champion.vegetarian.model.OrderEntry;

import java.util.List;

public interface OrderService {

    Integer createOrder(OrderRequest orderRequest);

    Order getOrderById(Integer orderId);

    public List<Order> getOrderByUserId(Integer userId);

     void insertCartEntryIntoOrderEntry(String cartUUID);

    void updateOrder(Integer orderId, OrderRequest orderRequest);

    void updateOrderPayment(String payment,Integer orderId);

    void updateOrderShipping(String shipping,Integer orderId);

    List<Order> getAllOrder();

    void deleteOrderById(Integer orderId);

}
