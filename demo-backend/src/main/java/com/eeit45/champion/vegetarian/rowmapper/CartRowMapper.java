package com.eeit45.champion.vegetarian.rowmapper;

import com.eeit45.champion.vegetarian.model.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRowMapper implements RowMapper<Cart> {
    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cart cart = new Cart();
        ObjectMapper mapper = new ObjectMapper();

        cart.setCartId(rs.getInt(("id")));
        cart.setUserId(rs.getInt("userId"));
        return cart;
    }

}
