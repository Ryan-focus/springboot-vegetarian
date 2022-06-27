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

     OrderEntry CartEntryToOrderEntry(CartEntry cartEntry);

}
