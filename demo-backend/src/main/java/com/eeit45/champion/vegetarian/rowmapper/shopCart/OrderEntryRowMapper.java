package com.eeit45.champion.vegetarian.rowmapper.shopCart;

import com.eeit45.champion.vegetarian.model.shopCart.OrderEntry;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderEntryRowMapper implements RowMapper<OrderEntry> {
    @Override
    public OrderEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
       OrderEntry orderEntry = new OrderEntry();
       orderEntry.setOrderEntryId(rs.getInt("orderEntryId"));
       orderEntry.setOrderUUID(rs.getString("orderUUID"));
       orderEntry.setProductId(rs.getInt("productId"));
       orderEntry.setQuantity(rs.getInt("quantity"));
       orderEntry.setOrderEntryPrice(rs.getInt("orderEntryPrice"));
        return orderEntry;
    }

}
