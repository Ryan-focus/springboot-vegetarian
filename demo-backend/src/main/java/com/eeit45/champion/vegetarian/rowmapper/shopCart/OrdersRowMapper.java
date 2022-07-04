package com.eeit45.champion.vegetarian.rowmapper.shopCart;

import com.eeit45.champion.vegetarian.model.shopCart.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrdersRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();

        order.setOrderId(rs.getInt("orderId"));
        order.setUserId(rs.getInt("userId"));
        order.setPayment(rs.getInt("payment"));
        order.setStatus(rs.getString("status"));

        Timestamp createdTime = rs.getTimestamp("createdTime");
        order.setCreatedTime(createdTime);

        Timestamp updatedTime = rs.getTimestamp("updatedTime");
        order.setUpdateTime(updatedTime);

        return order;
    }

}
