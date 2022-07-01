package com.eeit45.champion.vegetarian.service.impl;

import com.eeit45.champion.vegetarian.dao.OrderDao;
import com.eeit45.champion.vegetarian.dao.ProductDao;
import com.eeit45.champion.vegetarian.dto.BuyItem;
import com.eeit45.champion.vegetarian.dto.CreateOrderRequest;
import com.eeit45.champion.vegetarian.dto.OrderRequest;
import com.eeit45.champion.vegetarian.model.Order;
import com.eeit45.champion.vegetarian.model.OrderItem;
import com.eeit45.champion.vegetarian.model.Product;
import com.eeit45.champion.vegetarian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        return orderDao.createOrder(orderRequest);
    }


    @Transactional // 讓兩個table的訂單都是同時新增成功 or 同時新增失敗的狀況
    @Override
    public Integer createOrders(Integer userId, CreateOrderRequest createOrderRequest) {
        Integer totalAmount = 0 ;
        List<OrderItem> orderItemList = new ArrayList<>();

        // forLoop 整個List 裡面的 Product ， 去把值一一抓出去做+-*/
        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            //Cauculating Amount
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            //Transfer BuyItem To OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        //Created Order
        //在資料庫中，訂單是由兩個table所創立出來的，所以也要call兩個不同的table
        Integer orderId = orderDao.createOrders(userId,totalAmount); //在order table 創建一筆資料出來

        //在 order_item table 創立另一筆資料出來
        orderDao.createOrderItems(orderId,orderItemList);

        return null;
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

    @Override
    public void updateOrder(Integer orderId, OrderRequest orderRequest) {
        orderDao.updateOrder(orderId,orderRequest);
    }

    @Override
    public void updateOrderPayment(String payment, Integer orderId) {
        orderDao.updateOrderPayment(payment,orderId);
    }
    @Override
    public void updateOrderShipping(String shipping, Integer orderId) {
        orderDao.updateOrderShipping(shipping,orderId);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderDao.getAllOrder();
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        orderDao.deleteOrderById(orderId);
    }
}


