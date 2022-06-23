package com.eeit45.champion.vegetarian.rowmapper;

import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.CartEntry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartRowMapper implements RowMapper<Cart> {
    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cart cart = new Cart();
        ObjectMapper mapper = new ObjectMapper();

        cart.setCartId(rs.getInt(("id")));
        cart.setUserId(rs.getInt("userId"));
        List<CartEntry> cartEntries = new ArrayList<>();
        try {
           cartEntries = mapper.readValue(rs.getString("cartEntries"), new TypeReference<List<CartEntry>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        cart.setCartEntries(cartEntries);


        return cart;
    }

}
