package com.eeit45.champion.vegetarian.dao.shopCart.impl;

import com.eeit45.champion.vegetarian.dao.shopCart.OrderDao;
import com.eeit45.champion.vegetarian.dao.shopCart.ProductDao;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderEntryRequest;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderQueryParams;
import com.eeit45.champion.vegetarian.dto.shopCart.OrderRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Order;
import com.eeit45.champion.vegetarian.model.shopCart.OrderItem;
import com.eeit45.champion.vegetarian.rowmapper.shopCart.OrderItemRowMapper;
import com.eeit45.champion.vegetarian.rowmapper.shopCart.OrderRowMapper;
import com.eeit45.champion.vegetarian.rowmapper.shopCart.OrdersRowMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ProductDao productDao;

    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        String sql = "INSERT INTO `order` (userId,payment,shipping,status,orderUUID,createdTime)" +
                "VALUES(:userId, :payment, :shipping, :status, :orderUUID, :createdTime)";
        Map<String, Object> map = new HashMap<>();
        //生成訂單Id
        map.put("userId", orderRequest.getUserId());
        map.put("payment", "尚未付款");
        map.put("shipping", "尚未出貨");
        String status = "訂單成立";
        if (orderRequest.getOrderUUID() != null) {
            map.put("status", status);
        } else {
            map.put("status", "訂單未成立");
        }
        map.put("orderUUID", orderRequest.getOrderUUID());
        //生成現在日期
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        map.put("createdTime", timestamp);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        Integer orderId = keyHolder.getKey().intValue();

        return orderId;
    }


    //查詢訂單資料返回Controller 使用 Join Table
    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.amount, p.name " +
                "FROM order_item as oi " +
                "LEFT JOIN products as p ON oi.product_id = p.id " +
                "WHERE oi.order_id = :orderId";

        Map<String , Object> map = new HashMap<>();
        map.put("orderId" , orderId);

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map , new OrderItemRowMapper());


        return orderItemList;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = "SELECT count(*) FROM `orders` WHERE 1=1 ";

        Map<String , Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql,map,orderQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);

        return total;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = "SELECT order_id, user_id,total_amount, created_date, last_modified_date FROM `orders` " +
                "WHERE 1=1";

        Map<String , Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql,map,orderQueryParams);

        //排序
        //訂單列表，希望最新的排在最前面
        sql = sql + " ORDER BY created_date DESC";
        
        //分頁 
        sql = sql  + " LIMIT :limit OFFSET :offset"; 
        map.put("limit",orderQueryParams.getLimit());
        map.put("offset",orderQueryParams.getOffset());

        List<Order> orderList = namedParameterJdbcTemplate.query(sql,map,new OrdersRowMapper());

        return orderList;
    }


    @Override
    public Integer createOrders(Integer userId, Integer totalAmount) {
        String sql = "INSERT INTO `orders` (user_id, total_amount, created_date, last_modified_date) " +
                "VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate)";
        Map<String,Object> map = new HashMap<>();
        map.put("userId" , userId);
        map.put("totalAmount" , totalAmount);

        Date now = new Date();
        map.put("createdDate" , now);
        map.put("lastModifiedDate" , now);

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
            parameterSources[i].addValue("orderId",orderId);
            parameterSources[i].addValue("productId",orderItem.getProductId());
            parameterSources[i].addValue("quantity",orderItem.getQuantity());
            parameterSources[i].addValue("amount",orderItem.getAmount());
        }
        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT * FROM `order` WHERE orderId = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        if (orderList.size() > 0) {
            return orderList.get(0);
        } else {
            return null;
        }
    }

    //只能留一個方法，決定好記得刪掉。
    @Override
    public Order getOrdersById(Integer orderId) {
        String sql = "SELECT * FROM orders WHERE order_id = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrdersRowMapper());
        if (orderList.size() > 0) {
            return orderList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        String sql = "SELECT * FROM `order` WHERE userId= :userId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        if (orderList != null && orderList.size() > 0) {
            return orderList;
        } else {
            return null;
        }
    }

    @Override
    public Integer insertOrderEntry(OrderEntryRequest orderEntryRequest) {
        String sql = "Insert Into orderEntry (orderUUID,productId,quantity,entryPrice)" +
                "values(:orderId, :productId, :quantity, :orderEntryPrice)";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderEntryRequest.getOrderId());
        map.put("productId", orderEntryRequest.getProductId());
        map.put("quantity", orderEntryRequest.getQuantity());
        //根據product取出值後乘上數量在寫入
        int orderEntryPrice = productDao.getProductById(orderEntryRequest.getProductId()).getPrice() *
                orderEntryRequest.getQuantity();
        map.put("orderEntryPrice", orderEntryPrice);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int orderEntryId = keyHolder.getKey().intValue();

        return orderEntryId;
    }

    @Override
    public void insertCartEntryIntoOrderEntry(String cartUUID) {
        String sql = "INSERT INTO veganDB.orderEntry(productId,orderUUID,quantity,entryPrice) " +
                "SELECT productId,cartUUID,quantity,entryPrice " +
                "FROM veganDB.cartEntry " +
                "WHERE cartUUID = :cartUUID";
        Map<String, Object> map = new HashMap<>();
        map.put("cartUUID", cartUUID);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void updateOrder(Integer orderId, OrderRequest orderRequest) {
        String sql ="UPDATE veganDB.order SET userId = :userId, shipping = :shipping," +
                "payment = :payment,status = :status,updatedTime = :updatedTime " +
                "WHERE orderId = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId",orderRequest.getUserId());
        map.put("shipping",orderRequest.getShipping());
        map.put("payment",orderRequest.getPayment());
        map.put("status",orderRequest.getStatus());
        map.put("orderId",orderId);
        //放入更新時間
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        map.put("updatedTime",timestamp);
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void updateOrderPayment(String payment, Integer orderId) {
        String sql = "UPDATE veganDB.order SET payment = :payment where orderId = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("payment", payment);
        map.put("orderId", orderId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void updateOrderShipping(String shipping, Integer orderId) {
        String sql = "UPDATE veganDB.order SET shipping = :shipping where orderId = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("shipping", shipping);
        map.put("orderId", orderId);
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public List<Order> getAllOrder() {
        String sql = "SELECT * FROM veganDB.order";
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, new OrderRowMapper());
        return orderList;
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        String sql = "DELETE FROM veganDB.order WHERE orderId = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    private String addFilteringSql(String sql, Map<String, Object> map, OrderQueryParams orderQueryParams) {
        if(orderQueryParams.getUserId() != null) {
            sql = sql + " AND user_id = :userId";
            map.put("userId", orderQueryParams.getUserId());
        }
        return sql;
    }

}