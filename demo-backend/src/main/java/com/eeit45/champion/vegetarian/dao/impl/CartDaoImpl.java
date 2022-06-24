package com.eeit45.champion.vegetarian.dao.impl;

import com.eeit45.champion.vegetarian.dao.CartDao;
import com.eeit45.champion.vegetarian.dto.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.rowmapper.CartEntryRowMapper;
import com.eeit45.champion.vegetarian.rowmapper.CartRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CartDaoImpl implements CartDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Cart getCartById(Integer userId) {
        String sql = "SELECT * FROM cart WHERE userId= :userId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        Cart cart = namedParameterJdbcTemplate.queryForObject(sql, map, new CartRowMapper());


        return cart;
    }

    @Override
    public List<CartEntry> getCartEntriesById(Integer cartId) {
        String sql = "SELECT * FROM cartEntry WHERE cartId= :cartId";
        Map<String, Object> map = new HashMap<>();
        map.put("cartId", cartId);
        List<CartEntry> cartEntryList = namedParameterJdbcTemplate.query(sql, map, new CartEntryRowMapper());
        if (cartEntryList.size() > 0) {
            return cartEntryList;
        } else {
            return null;
        }
    }

    @Override
    public Integer AddToCart(CartEntryRequest cartEntryRequest) {
        String sql = "INSERT INTO cartEntry(cartId,productId,quantity,entryPrice)" +
                "VALUES (:cartId, :productId, :quantity, :entryPrice)";
        Map<String, Object> map = new HashMap<>();
        map.put("cartId", cartEntryRequest.getCartId());
        map.put("productId", cartEntryRequest.getProductId());
        map.put("quantity", cartEntryRequest.getQuantity());
        map.put("entryPrice", cartEntryRequest.getEntryPrice());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int id = keyHolder.getKey().intValue();

        return id;
    }

    @Override
    public void deleteProductFromCartById(Integer entryId) {
        String sql = "DELETE FORM cartEntry WHERE entryId= :entryId";
        Map<String, Object> map = new HashMap<>();
        map.put("entryId", entryId);
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void updateQuantity(Integer entryId, CartEntryRequest cartEntryRequest) {
        String sql = "Update cartEntry SET quantity=:quantity where entryId= :entryId ";
        Map<String, Object> map = new HashMap<>();
        map.put("quantity", cartEntryRequest.getQuantity());
        map.put("entryId", entryId);

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public Integer CreateNewCart(Integer userId) {
        String sql = "INSERT into cart (userId) VALUES (:userId)";
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        namedParameterJdbcTemplate.update(sql,map);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int cartId = keyHolder.getKey().intValue();

        return cartId;
    }
}
