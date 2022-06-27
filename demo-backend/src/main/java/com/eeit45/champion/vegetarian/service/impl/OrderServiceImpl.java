package com.eeit45.champion.vegetarian.service.impl;

import com.eeit45.champion.vegetarian.dao.OrderDao;
import com.eeit45.champion.vegetarian.dto.OrderEntryRequest;
import com.eeit45.champion.vegetarian.dto.OrderRequest;
import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.model.Order;
import com.eeit45.champion.vegetarian.model.OrderEntry;
import com.eeit45.champion.vegetarian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        return orderDao.createOrder(orderRequest);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        return orderDao.getOrderByUserId(userId);
    }

    @Override
    public void insertCartEntryIntoOrderEntry(String cartUUID) {
        orderDao.insertCartEntryIntoOrderEntry(cartUUID);
    }
}


