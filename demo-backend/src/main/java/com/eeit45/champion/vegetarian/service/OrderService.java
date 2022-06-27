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

    Integer insertOrderEntry(OrderEntryRequest orderEntryRequest);

    OrderEntry CartEntryToOrderEntry(CartEntry cartEntry);
}
