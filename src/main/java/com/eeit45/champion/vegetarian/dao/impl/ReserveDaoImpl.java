package com.eeit45.champion.vegetarian.dao.impl;

import com.eeit45.champion.vegetarian.dao.ReserveDao;
import com.eeit45.champion.vegetarian.dto.ReserveRequest;
import com.eeit45.champion.vegetarian.model.Reserve;
import com.eeit45.champion.vegetarian.rowmapper.ReserveRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReserveDaoImpl implements ReserveDao {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Reserve getReserveById(Integer reserveId) {
        String sql = "SELECT * FROM reserve WHERE reserveId = :reserveId";

        Map<String,Object> map = new HashMap<>();
        map.put("reserveId" , reserveId);

        List<Reserve> reserveList = namedParameterJdbcTemplate.query(sql, map, new ReserveRowMapper());
        if(reserveList.size() > 0){
            return reserveList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createReserve(ReserveRequest reserveRequest) {

        String sql = "INSERT INTO reserve ( reserveDate, count, reserveTime, restaurantId, userId)" +
                     "VALUES (:reserveDate, :count, :reserveTime, :restaurantId, :userId)";

        Map<String, Object> map = new HashMap<>();

        map.put("reserveDate" ,reserveRequest.getReserveDate());

        map.put("count" ,reserveRequest.getCount());

        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        map.put("reserveTime" ,timestamp);

        map.put("restaurantId" ,reserveRequest.getRestaurantId());
        map.put("userId" ,reserveRequest.getUserId());


        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);

        int reserveId = keyHolder.getKey().intValue();

        return reserveId;
    }
}
