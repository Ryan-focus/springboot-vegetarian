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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDaoImpl implements CartDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Cart getCartById(Integer userId) {
        String sql = "SELECT * FROM cart WHERE userId= :userId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        Cart cart = namedParameterJdbcTemplate.queryForObject(sql, map, new  CartRowMapper());


        return cart;
    }

    @Override
    public List<CartEntry> getCartEntriesById(Integer cartId) {
        String sql = "SELECT * FROM cartEntry WHERE cartId= :cartId";
        Map<String, Object> map = new HashMap<>();
        map.put("cartId",cartId);
        List<CartEntry> cartEntryList = namedParameterJdbcTemplate.query(sql,map,new CartEntryRowMapper());
        if(cartEntryList.size()>0) {
            return cartEntryList;
        }else {
            return null;
        }
    }

    @Override
    public Integer AddToCart(CartEntryRequest cartEntryRequest) {
        String sql ="INSERT INTO cartEntry(cartId,productId,quantity,entryPrice)" +
                "VALUES (:cartId, :productId, :quantity, :entryPrice)";
        Map<String, Object> map = new HashMap<>();
        map.put("cartId",cartEntryRequest.getCartId());
        map.put("productId",cartEntryRequest.getProductId());
        map.put("quantity",cartEntryRequest.getQuantity());
        map.put("entryPrice",cartEntryRequest.getEntryPrice());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int id = keyHolder.getKey().intValue();

        return id;
    }

    @Override
    public void deleteProductFromCartById(Integer productId) {

    }

    @Override
    public void updateQuantity(Integer productId, CartEntryRequest cartEntryRequest) {

    }
}
