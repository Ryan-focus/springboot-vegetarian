package com.eeit45.champion.vegetarian.dao.customer.impl;

import com.eeit45.champion.vegetarian.dao.customer.PosDao;
import com.eeit45.champion.vegetarian.dto.customer.PosRequest;
import com.eeit45.champion.vegetarian.model.customer.Pos;

import com.eeit45.champion.vegetarian.rowmapper.customer.PosRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PosDaoImpl implements PosDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Pos getPosById(Integer posId) {
        String sql = "SELECT * FROM pos WHERE posId = :posId";

        Map<String, Object> map = new HashMap<>();
        map.put("posId", posId);

        List<Pos> posList = namedParameterJdbcTemplate.query(sql, map, new PosRowMapper());
        if (posList.size() > 0) {
            return posList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer buildPos(Integer businessId, PosRequest posRequest) {
        String sql = "INSERT INTO pos (businessId,validDate,expiryDate,visitors,turnOver) VALUES " +
                "(:businessId,:validDate,:expiryDate,:visitors,:turnOver)";

        Map<String, Object> map = new HashMap<>();
        map.put("businessId", businessId);
        map.put("validDate", posRequest.getValidDate());

        map.put("expiryDate", posRequest.getExpiryDate());
        map.put("visitors", 0);
        map.put("turnOver", 0);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer posId = keyHolder.getKey().intValue();

        return posId;
    }

    //後台用
    @Override
    public List<Pos> getAllPosList() {
        String sql = "SELECT * FROM pos";

        List<Pos> posList = namedParameterJdbcTemplate.query(sql,new PosRowMapper());

        if(posList != null) return posList;

        return null;
    }
}
