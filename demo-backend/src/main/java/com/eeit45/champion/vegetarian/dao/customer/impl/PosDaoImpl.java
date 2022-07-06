package com.eeit45.champion.vegetarian.dao.customer.impl;

import com.eeit45.champion.vegetarian.dao.customer.PosDao;
import com.eeit45.champion.vegetarian.dto.customer.PosRequest;
import com.eeit45.champion.vegetarian.model.customer.Pos;

import com.eeit45.champion.vegetarian.model.customer.PosBusiness;
import com.eeit45.champion.vegetarian.rowmapper.customer.PosBusinessRowMapper;
import com.eeit45.champion.vegetarian.rowmapper.customer.PosRowMapper;

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
public class PosDaoImpl implements PosDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<PosBusiness> getPosBusinessByPosId(Integer posId) {
        String sql = "SELECT pb.posBusinessId, pb.posId, pb.businessId, pb.visitors, pb.turnOver, b.businessName " +
                "FROM posbusiness as pb " +
                "LEFT JOIN business as b ON pb.businessId = b.businessId " +
                "WHERE pb.posId = :posId";

        Map<String, Object> map = new HashMap<>();
        map.put("posId", posId);

        List<PosBusiness> posBusinessList = namedParameterJdbcTemplate.query(sql, map, new PosBusinessRowMapper());

        return posBusinessList;
    }

    @Override
    public void buildPosBusiness(Integer posId, Integer businessId) {
        String sql = "INSERT INTO posbusiness(posId, businessId , visitors , turnOver) " +
                "VALUES(:posId , :businessId , :visitors, :turnOver)";

        Map<String, Object> map = new HashMap<>();
        map.put("posId", posId);
        map.put("businessId", businessId);
        map.put("visitors", 0);
        map.put("turnOver", 0);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);
    }

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
        String sql = "INSERT INTO pos (businessId,validDate,expiryDate) VALUES " +
                "(:businessId,:validDate,:expiryDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("businessId", businessId);
        map.put("validDate", posRequest.getValidDate());

        map.put("expiryDate", posRequest.getExpiryDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int posId = keyHolder.getKey().intValue();

        return posId;
    }

    //後台用
    @Override
    public List<Pos> getAllPosList() {
        String sql = "SELECT * FROM pos";
        return namedParameterJdbcTemplate.query(sql,new PosRowMapper());
    }

}
