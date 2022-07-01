package com.eeit45.champion.vegetarian.dao;

import com.eeit45.champion.vegetarian.dto.OrderEntryRequest;
import com.eeit45.champion.vegetarian.dto.OrderRequest;
import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.model.Order;
import com.eeit45.champion.vegetarian.model.OrderEntry;

import java.util.List;

public interface OrderDao {

     Integer createOrder(OrderRequest orderRequest);

     Order getOrderById(Integer orderId);

     List<Order> getOrderByUserId(Integer userId);

     Integer insertOrderEntry(OrderEntryRequest orderEntryRequest);

     void insertCartEntryIntoOrderEntry(String cartUUID);

     void updateOrder(Integer orderId,OrderRequest orderRequest);

     void updateOrderPayment(String payment,Integer orderId);

     void updateOrderShipping(String shipping,Integer orderId);

     List<Order> getAllOrder();

     void deleteOrderById(Integer orderId);

}
