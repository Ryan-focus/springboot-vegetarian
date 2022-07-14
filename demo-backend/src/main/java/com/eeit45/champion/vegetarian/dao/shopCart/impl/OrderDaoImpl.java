package com.eeit45.champion.vegetarian.dao.shopCart.impl;

import com.eeit45.champion.vegetarian.dao.shopCart.OrderDao;
import com.eeit45.champion.vegetarian.dao.shopCart.ProductDao;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderQueryParams;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Order;
import com.eeit45.champion.vegetarian.model.shopCart.OrderItem;
import com.eeit45.champion.vegetarian.rowmapper.shopCart.OrderItemRowMapper;
import com.eeit45.champion.vegetarian.rowmapper.shopCart.OrdersRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    //查詢訂單資料返回Controller 使用 Join Table
    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.amount, p.productName,p.productImage " +
                "FROM order_item as oi " +
                "LEFT JOIN product as p ON oi.product_id = p.productId " +
                "WHERE oi.order_id = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());


        return orderItemList;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = "SELECT count(*) FROM `order` WHERE 1=1 ";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, orderQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = "SELECT orderId, userId,payment,status, createdTime, updatedTime FROM `order` " +
                "WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, orderQueryParams);

        //排序
        //訂單列表，希望最新的排在最前面
        sql = sql + " ORDER BY createdTime DESC";

        //分頁 
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", orderQueryParams.getLimit());
        map.put("offset", orderQueryParams.getOffset());

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrdersRowMapper());

        return orderList;
    }


    @Override
    public Integer createOrders(Integer userId, Integer totalAmount) {
        String sql = "INSERT INTO `order` (userId, payment, createdTime, updatedTime) " +
                "VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer orderId = keyHolder.getKey().intValue();

        return orderId;
    }


    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        //使用for loop 一條一條插入sql 效率低
        //建議使用batchUpdates 一次性插整張訂單
        String sql = "INSERT INTO order_item(order_id, product_id , quantity , amount) " +
                "VALUES(:orderId , :productId , :quantity, :amount)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];

        for (int i = 0; i < orderItemList.size(); i++
        ) {
            OrderItem orderItem = orderItemList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId", orderId);
            parameterSources[i].addValue("productId", orderItem.getProductId());
            parameterSources[i].addValue("quantity", orderItem.getQuantity());
            parameterSources[i].addValue("amount", orderItem.getAmount());
        }
        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }


    //只能留一個方法，決定好記得刪掉。
    @Override
    public Order getOrdersById(Integer orderId) {
        String sql = "SELECT * FROM `order` WHERE orderId = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrdersRowMapper());
        if (orderList.size() > 0) {
            return orderList.get(0);
        } else {
            return null;
        }
    }

    private String addFilteringSql(String sql, Map<String, Object> map, OrderQueryParams orderQueryParams) {
        if (orderQueryParams.getUserId() != null) {
            sql = sql + " AND userId = :userId";
            map.put("userId", orderQueryParams.getUserId());
        }
        return sql;
    }

    @Override
    public List<Order> getAllOrders() {
        String sql = "select * from veganDB.order";

        List<Order> orderList = namedParameterJdbcTemplate.query(sql,new OrdersRowMapper());
        if (orderList!=null){
            return orderList;
        }else {
            return null;
        }
    }

    @Override
    public void updateOrder(Integer orderId, OrderRequest orderRequest) {
    String sql = "UPDATE veganDB.order SET payment = :payment,status = :status, updatedTime= :updatedTime " +
            "WHERE orderId = :orderId";
        Map<String , Object> map = new HashMap<>();
        map.put("payment",orderRequest.getPayment());
        map.put("status",orderRequest.getStatus());
        map.put("orderId",orderId);
        map.put("updatedTime",new Date());

        namedParameterJdbcTemplate.update(sql,map);
    }


}