package com.eeit45.champion.vegetarian.dao.impl;

import com.eeit45.champion.vegetarian.dao.OrderDao;
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
import java.util.*;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        String sql="INSERT INTO order (orderId,userId,payment,shipping,orderEntry,status,createdTime)" +
                "VALUES(:orderId, :userId, :payment, :shipping, :orderEntry, :status, :createdTime)";
        Map<String, Object> map = new HashMap<>();
        //生成訂單Id
        UUID uuid = UUID.randomUUID();

        map.put("orderId",uuid);
        map.put("userId",orderRequest.getUserId());
        map.put("payment",orderRequest.getPayment());
        map.put("shipping",orderRequest.getShipping());
        map.put("orderEntry",orderRequest.getCartEntries().toString());
        map.put("status",orderRequest.getStatus());
        //生成現在日期
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        map.put("createdTime",timestamp);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder );


        return null;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT * FROM reserve WHERE orderId = :orderId";
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
        String sql = "SELECT * FROM reserve WHERE userId = :userId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        if (orderList.size() > 0) {
            return orderList;
        } else {

            return null;
        }
    }
}