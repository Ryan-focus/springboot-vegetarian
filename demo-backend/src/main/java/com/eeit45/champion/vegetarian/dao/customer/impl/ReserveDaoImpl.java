package com.eeit45.champion.vegetarian.dao.customer.impl;

import com.eeit45.champion.vegetarian.dao.customer.ReserveDao;
import com.eeit45.champion.vegetarian.dto.customer.ReserveRequest;
import com.eeit45.champion.vegetarian.model.customer.Reserve;
import com.eeit45.champion.vegetarian.rowmapper.customer.ReserveRowMapper;
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
public class ReserveDaoImpl implements ReserveDao {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    //查詢單一對象，回傳使用Reserve(物件)
    @Override
    public Reserve getReserveById(Integer businessId ,Integer  reserveId) {
        String sql = "SELECT * FROM reserve WHERE businessId = :businessId AND reserveId = :reserveId";

        Map<String, Object> map = new HashMap<>();
        map.put("businessId" ,businessId);
        map.put("reserveId", reserveId);

        List<Reserve> reserveList = namedParameterJdbcTemplate.query(sql, map, new ReserveRowMapper());
        if (reserveList.size() > 0) {
            return reserveList.get(0);
        } else {
            return null;
        }
    }


    //使用者前台發送一筆創建訂單
    @Override
    public Integer createReserve(Integer businessId, ReserveRequest reserveRequest) {

        String sql = "INSERT INTO reserve ( reserveDateTime, adult, child, baby, reserveTime, restaurantId,businessId, reserveName , reservePhone)"  +
                "VALUES (:reserveDate, :adult , :child , :baby , :reserveTime, :restaurantId, :businessId, :reserveName , :reservePhone)";

        Map<String, Object> map = new HashMap<>();

        map.put("reserveDate", reserveRequest.getReserveDate());

        map.put("adult" ,reserveRequest.getAdult() );

        map.put("child" ,reserveRequest.getChild());


        map.put("baby" ,reserveRequest.getBaby() );


        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        map.put("reserveTime", timestamp);

        map.put("restaurantId", reserveRequest.getRestaurantId());
        map.put("businessId", businessId);
        map.put("reserveName", reserveRequest.getReserveName());
        map.put("reservePhone", reserveRequest.getReservePhone());


        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer reserveId = keyHolder.getKey().intValue();

        return reserveId;
    }

    @Override
    public List<Reserve> getAllReserve(Integer businessId) {
        String sql = "select * from reserve WHERE businessId = :businessId ORDER BY reserveDateTime  DESC";

        Map<String , Object > map = new HashMap<>();
        map.put("businessId",businessId);

        List<Reserve> reserveList = namedParameterJdbcTemplate.query(sql,map,new ReserveRowMapper());
        if (reserveList!=null){
            return reserveList;
        }else {
            return null;
        }
    }
}