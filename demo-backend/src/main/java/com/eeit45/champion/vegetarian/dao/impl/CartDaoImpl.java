package com.eeit45.champion.vegetarian.dao.impl;

import com.eeit45.champion.vegetarian.dao.CartDao;
import com.eeit45.champion.vegetarian.dao.ProductDao;
import com.eeit45.champion.vegetarian.dto.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.model.Product;
import com.eeit45.champion.vegetarian.rowmapper.CartEntryRowMapper;
import com.eeit45.champion.vegetarian.rowmapper.CartRowMapper;
import com.eeit45.champion.vegetarian.util.CartEntryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class CartDaoImpl implements CartDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private ProductDao productDao;

    @Override
    public Cart getCartById(Integer userId) {
        String sql = "SELECT * FROM cart WHERE userId= :userId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<Cart> cart = namedParameterJdbcTemplate.query(sql, map, new CartRowMapper());
        if (cart.size() > 0) {
            return cart.get(0);
        } else {
            return null;
        }
    }

    public CartEntryDetail showCartEntryDetail(CartEntry cartEntry) {
        Product product = productDao.getProductById(cartEntry.getProductId());
        CartEntryDetail cartEntryDetail = new CartEntryDetail();
        cartEntryDetail.setEntryId(cartEntry.getEntryId());
        cartEntryDetail.setCartUUID(cartEntry.getCartUUID());
        cartEntryDetail.setQuantity(cartEntry.getQuantity());
        cartEntryDetail.setEntryPrice(cartEntry.getEntryPrice());
        cartEntryDetail.setProductId(cartEntry.getProductId());
        cartEntryDetail.setProduct(product);

        return cartEntryDetail;
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
    public CartEntry getSingleCartEntry(Integer cartEntryId) {
        String sql = "SELECT * FROM cartEntry WHERE cartEntryId= :cartEntryId";
        Map<String, Object> map = new HashMap<>();
        map.put("cartEntryId", cartEntryId);
        List<CartEntry> cartEntry = namedParameterJdbcTemplate.query(sql, map, new CartEntryRowMapper());
        if (cartEntry.size() > 0) {
            return cartEntry.get(0);
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
        Product product = productDao.getProductById(cartEntryRequest.getProductId());
        int productPrice = product.getPrice();
        map.put("entryPrice", cartEntryRequest.getQuantity() * productPrice);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int cartEntryId = keyHolder.getKey().intValue();

        return cartEntryId;
    }

    @Override
    public void deleteProductFromCartById(Integer cartEntryId) {
        String sql = "DELETE FROM cartEntry WHERE cartEntryId= :cartEntryId";
        Map<String, Object> map = new HashMap<>();
        map.put("cartEntryId", cartEntryId);
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void updateQuantity(Integer cartEntryId, CartEntryRequest cartEntryRequest) {
        String sql = "Update cartEntry SET quantity=:quantity, entryPrice= :entryPrice where cartEntryId= :cartEntryId ";
        Map<String, Object> map = new HashMap<>();
        map.put("quantity", cartEntryRequest.getQuantity());
        map.put("cartEntryId", cartEntryId);
        //用productId取得價格，再乘上新的數量的到新的價格
        Product product = productDao.getProductById(cartEntryRequest.getProductId());
        map.put("entryPrice", product.getPrice() * cartEntryRequest.getQuantity());

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public Integer CreateNewCart(Integer userId) {
        String sql = "INSERT into cart (userId,cartUUID) VALUES ( :userId, :cartUUID)";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        UUID uuid = UUID.randomUUID();
        //去掉UUID的斜線
        String uuidNoneDash = uuid.toString().replace("-","");
        map.put("cartUUID",uuidNoneDash);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int cartId = keyHolder.getKey().intValue();
        return cartId;
    }

    @Override
    public void deleteCartById(Integer cartId) {
        String sql = "DELETE FROM cart WHERE cartId= :cartId";
        Map<String,Object> map = new HashMap<>();
        map.put("cartId",cartId);
        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public void deleteCartEntryById(Integer cartId) {
        String sql = "DELETE FROM cartEntry WHERE cartId= :cartId";
        Map<String,Object> map = new HashMap<>();
        map.put("cartId",cartId);
        namedParameterJdbcTemplate.update(sql,map);
    }
}
