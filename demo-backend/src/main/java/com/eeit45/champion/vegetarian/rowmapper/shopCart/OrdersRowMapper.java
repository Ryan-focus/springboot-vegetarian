package com.eeit45.champion.vegetarian.rowmapper.shopCart;

import com.eeit45.champion.vegetarian.model.shopCart.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrdersRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        ObjectMapper mapper = new ObjectMapper();

        order.setOrderId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setPayment(rs.getString("total_amount"));
        Timestamp createdTime = rs.getTimestamp("created_date");
        order.setCreatedTime(createdTime);
        Timestamp updatedTime = rs.getTimestamp("last_modified_date");
        order.setUpdateTime(updatedTime);
        return order;
    }

}
