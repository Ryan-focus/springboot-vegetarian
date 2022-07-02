package com.eeit45.champion.vegetarian.rowmapper.shopCart;

import com.eeit45.champion.vegetarian.model.shopCart.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        ObjectMapper mapper = new ObjectMapper();

        order.setOrderId(rs.getInt("orderId"));
        order.setUserId(rs.getInt("userId"));
        order.setShipping(rs.getString("shipping"));
        order.setStatus(rs.getString("status"));
        order.setPayment(rs.getString("payment"));
        order.setOrderUUID(rs.getString("orderUUID"));

        Timestamp createdTime = rs.getTimestamp("createdTime");
        order.setCreatedTime(createdTime);
        Timestamp updatedTime = rs.getTimestamp("updatedTime");
        order.setUpdateTime(updatedTime);
        return order;
    }

}
