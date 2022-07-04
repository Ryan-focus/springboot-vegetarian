package com.eeit45.champion.vegetarian.rowmapper.shopCart;


import com.eeit45.champion.vegetarian.model.shopCart.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(rs.getInt("order_item_id"));
        orderItem.setOrderId(rs.getInt("order_id"));
        orderItem.setProductId(rs.getInt("product_id"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setAmount(rs.getInt("amount"));
        
        //可以選擇使用擴充原有的class 去接這個variable
        //或者可以選擇創建新的Class 去接這些值
//        orderItem.setProductName(rs.getString("name"));
//        orderItem.setImage(rs.getString("image"));

        return orderItem;
    }
}
