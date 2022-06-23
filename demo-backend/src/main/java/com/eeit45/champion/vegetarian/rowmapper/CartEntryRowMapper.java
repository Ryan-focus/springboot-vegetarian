package com.eeit45.champion.vegetarian.rowmapper;

import com.eeit45.champion.vegetarian.model.CartEntry;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartEntryRowMapper implements RowMapper<CartEntry> {
    @Override
    public CartEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        CartEntry cartEntry = new CartEntry();

        cartEntry.setCartId(rs.getInt("cartId"));
        cartEntry.setProductId(rs.getInt("productId"));
        cartEntry.setQuantity(rs.getInt("quantity"));
        cartEntry.setEntryPrice(rs.getInt("quantity") * rs.getInt("price"));

        return cartEntry;
    }

}
