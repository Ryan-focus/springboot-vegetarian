package com.eeit45.champion.vegetarian.dao.impl;

import com.eeit45.champion.vegetarian.dao.CartDao;

import com.eeit45.champion.vegetarian.dto.CartRequest;

import com.eeit45.champion.vegetarian.model.Cart;

import com.eeit45.champion.vegetarian.model.Product;
import com.eeit45.champion.vegetarian.rowmapper.CartRowMapper;
import com.eeit45.champion.vegetarian.rowmapper.ProductRowMapper;
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
    public Cart addToCart(Integer productId) {
        return null;
    }

    @Override
    public Cart getCartItemById(Integer userId) {
        String sql = "SELECT * FROM cart WHERE userId = :userId";

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);

        List<Cart> cartList = namedParameterJdbcTemplate.query(sql, map, new CartRowMapper());
        if(cartList.size() > 0){
            return cartList.get(0);
        }else{
            return null;
        }

    }

    @Override
    public Integer insertCartItem(CartRequest cartRequest) {
        String sql = "INSERT INTO cart ( userId, productId, quantity)" +
                "VALUES (:userId, :productId, :quantity)";

        Map<String,Object> map = new HashMap<>();
        map.put("userId" ,cartRequest.getUserId());
        map.put("productId" ,cartRequest.getProductId());
        map.put("quantity" ,cartRequest.getQuantity());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);

        int userId = keyHolder.getKey().intValue();

        return userId;
    }

    @Override
    public void updateQuantity(Integer productId, CartRequest cartRequest) {

    }

    @Override
    public void deleteCartItem(Integer productId) {
        String sql = "DELETE FROM cart WHERE id= :productId";

        Map<String,Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql,map);
    }
}
