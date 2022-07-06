package com.eeit45.champion.vegetarian.service.shopCart.impl;

import com.eeit45.champion.vegetarian.dao.UserDao;
import com.eeit45.champion.vegetarian.dao.shopCart.OrderDao;
import com.eeit45.champion.vegetarian.dao.shopCart.ProductDao;
import com.eeit45.champion.vegetarian.dto.shopCart.BuyItem;
import com.eeit45.champion.vegetarian.dto.shopCart.CreateOrderRequest;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderQueryParams;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderRequest;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.model.shopCart.Order;
import com.eeit45.champion.vegetarian.model.shopCart.OrderItem;
import com.eeit45.champion.vegetarian.model.shopCart.Product;
import com.eeit45.champion.vegetarian.service.shopCart.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;


    @Transactional // 讓兩個table的訂單都是同時新增成功 or 同時新增失敗的狀況
    @Override
    public Integer createOrders(Integer userId, CreateOrderRequest createOrderRequest) {
        //訂單是帳號功能的附屬品 ， 要先創建帳號才能夠創建訂單
        //先檢查帳號是否存在

        User user = userDao.getUserById(userId);

        if(user == null){
            log.warn("該userId{}不存在",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        int totalAmount = 0;

        List<OrderItem> orderItemList = new ArrayList<>();

        // for Loop 整個List 裡面的 Product ， 去把值一一抓出去做+-*/
        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());


            //扣掉庫存的Code會寫在這邊，若Product本身會有 stock欄位的話
            //會由此去判斷庫存是否足夠，若不夠的話則拒絕創建訂單。
            //前端若有調整庫存數據的code，應為送一筆[ { productId : stock } ,{ productId : stock }]
            //針對product欄位去調整庫存，且令product的stock欄位為負值
            if (product == null) {
                log.warn("商品{}不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            else if (product.getStock() < buyItem.getQuantity()){
                log.warn("商品{}庫存數量不足，無法購買。 剩餘庫存{}，欲購買數量{}",
                   buyItem.getProductId(), product.getStock(),buyItem.getQuantity());
                   throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            //扣除商品庫存
            productDao.updateStock(product.getProductId(),product.getStock() - buyItem.getQuantity());

            //計算總金額
            int amount = buyItem.getQuantity() * product.getProductPrice();
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
        Integer orderId = orderDao.createOrders(userId, totalAmount); //在order table 創建一筆資料出來

        //在 order_item table 創立另一筆資料出來
        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {

        List<Order> orderList = orderDao.getOrders(orderQueryParams);

        for (Order order : orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());

            order.setOrderItemList(orderItemList);
        }

        return orderList;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }


    @Override
    public Order getOrdersById(Integer orderId) {
        Order order = orderDao.getOrdersById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public void updateOrder(Integer orderId, OrderRequest orderRequest) {
        orderDao.updateOrder(orderId,orderRequest);
    }
}


