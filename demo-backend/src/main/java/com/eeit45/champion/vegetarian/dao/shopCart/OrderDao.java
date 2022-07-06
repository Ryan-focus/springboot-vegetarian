package com.eeit45.champion.vegetarian.dao.shopCart;

import com.eeit45.champion.vegetarian.dto.shopCart.OrderQueryParams;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderRequest;
import com.eeit45.champion.vegetarian.dto.shopCart.ProductRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Order;
import com.eeit45.champion.vegetarian.model.shopCart.OrderItem;

import java.util.List;

public interface OrderDao {




     List<OrderItem> getOrderItemsByOrderId(Integer orderId);

     Integer countOrder(OrderQueryParams orderQueryParams);

     List<Order> getOrders(OrderQueryParams orderQueryParams);

     //取全部的資料給後台用
     List<Order> getAllOrders();

     Integer createOrders(Integer userId, Integer totalAmount);

     void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

     //配合寫Left Join 方法
     Order getOrdersById(Integer orderId);

     void updateOrder(Integer orderId, OrderRequest orderRequest);




}
