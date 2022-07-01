package com.eeit45.champion.vegetarian.dao.impl;

import com.eeit45.champion.vegetarian.dao.OrderDao;
import com.eeit45.champion.vegetarian.dao.ProductDao;
import com.eeit45.champion.vegetarian.dto.OrderEntryRequest;
import com.eeit45.champion.vegetarian.dto.OrderRequest;
import com.eeit45.champion.vegetarian.model.Order;
import com.eeit45.champion.vegetarian.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ProductDao productDao;

    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        String sql = "INSERT INTO veganDB.order (userId,payment,shipping,status,orderUUID,createdTime)" +
                "VALUES(:userId, :payment, :shipping, :status, :orderUUID, :createdTime)";
        Map<String, Object> map = new HashMap<>();
        //生成訂單Id
        map.put("userId", orderRequest.getUserId());
        map.put("payment", "尚未付款");
        map.put("shipping", "尚未出貨");
        String status = "訂單成立";
        if (orderRequest.getOrderUUID() != null) {
            map.put("status", status);
        } else {
            map.put("status", "訂單未成立");
        }
        map.put("orderUUID", orderRequest.getOrderUUID());
        //生成現在日期
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        map.put("createdTime", timestamp);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT * FROM veganDB.order WHERE orderId = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        if (orderList.size() > 0) {
            return orderList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        String sql = "SELECT * FROM veganDB.order WHERE userId= :userId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        if (orderList != null && orderList.size() > 0) {
            return orderList;
        } else {
            return null;
        }
    }

    @Override
    public Integer insertOrderEntry(OrderEntryRequest orderEntryRequest) {
        String sql = "Insert Into orderEntry (orderId,productId,quantity,orderEntryPrice)" +
                "values(:orderId, :productId, :quantity, :orderEntryPrice)";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderEntryRequest.getOrderId());
        map.put("productId", orderEntryRequest.getProductId());
        map.put("quantity", orderEntryRequest.getQuantity());
        //根據product取出值後乘上數量在寫入
        int orderEntryPrice = productDao.getProductById(orderEntryRequest.getProductId()).getPrice() *
                orderEntryRequest.getQuantity();
        map.put("orderEntryPrice", orderEntryPrice);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int orderEntryId = keyHolder.getKey().intValue();

        return orderEntryId;
    }

    @Override
    public void insertCartEntryIntoOrderEntry(String cartUUID) {
        String sql = "INSERT INTO veganDB.orderEntry(productId,orderUUID,quantity,entryPrice) " +
                "SELECT productId,cartUUID,quantity,entryPrice " +
                "FROM veganDB.cartEntry " +
                "WHERE cartUUID = :cartUUID";
        Map<String, Object> map = new HashMap<>();
        map.put("cartUUID", cartUUID);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void updateOrder(Integer orderId, OrderRequest orderRequest) {
        String sql ="UPDATE veganDB.order SET userId = :userId, shipping = :shipping," +
                "payment = :payment,status = :status,updatedTime = :updatedTime " +
                "WHERE orderId = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId",orderRequest.getUserId());
        map.put("shipping",orderRequest.getShipping());
        map.put("payment",orderRequest.getPayment());
        map.put("status",orderRequest.getStatus());
        map.put("orderId",orderId);
        //放入更新時間
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        map.put("updatedTime",timestamp);
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void updateOrderPayment(String payment, Integer orderId) {
        String sql = "UPDATE veganDB.order SET payment = :payment where orderId = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("payment", payment);
        map.put("orderId", orderId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void updateOrderShipping(String shipping, Integer orderId) {
        String sql = "UPDATE veganDB.order SET shipping = :shipping where orderId = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("shipping", shipping);
        map.put("orderId", orderId);
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public List<Order> getAllOrder() {
        String sql = "SELECT * FROM veganDB.order";
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, new OrderRowMapper());
        return orderList;
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        String sql = "DELETE FROM veganDB.order WHERE orderId = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        namedParameterJdbcTemplate.update(sql, map);
    }
}