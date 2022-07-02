package com.eeit45.champion.vegetarian.dao.shopCart;

import com.eeit45.champion.vegetarian.dto.shopCart.OrderEntryRequest;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Order;
import com.eeit45.champion.vegetarian.model.shopCart.OrderItem;

import java.util.List;

public interface OrderDao {

     Integer createOrder(OrderRequest orderRequest);

     Integer createOrders(Integer userId, Integer totalAmount);

     void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

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
