package com.eeit45.champion.vegetarian.dao.customer.impl;

import com.eeit45.champion.vegetarian.dao.customer.PosDao;
import com.eeit45.champion.vegetarian.dto.customer.PosQueryParams;
import com.eeit45.champion.vegetarian.dto.customer.PosRequest;
import com.eeit45.champion.vegetarian.model.customer.Business;
import com.eeit45.champion.vegetarian.model.customer.Pos;

import com.eeit45.champion.vegetarian.model.customer.PosBusiness;
import com.eeit45.champion.vegetarian.rowmapper.customer.BusinessRowMapper;
import com.eeit45.champion.vegetarian.rowmapper.customer.PosBusinessRowMapper;
import com.eeit45.champion.vegetarian.rowmapper.customer.PosRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

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
        String bSql = "SELECT * FROM business WHERE businessId = :businessId";
        String sql = "INSERT INTO posbusiness(posId, businessId , visitors , turnOver) " +
                "VALUES(:posId , :businessId , :visitors, :turnOver)";

        Map<String , Object > mapBusiness = new HashMap<>();
        mapBusiness.put("businessId" , businessId);
        List<Business> businessList =namedParameterJdbcTemplate.query(bSql,mapBusiness,new BusinessRowMapper());
        Business business = businessList.get(0);


        Map<String, Object> map = new HashMap<>();
        map.put("posId", posId);
        map.put("businessId", businessId);
        map.put("visitors", 0);
        map.put("turnOver", 0);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);
    }

    @Override
    public Pos getPosByBusinessId(Integer businessId) {
        String sql = "SELECT * FROM pos WHERE businessId = :businessId";
        Map<String, Object> map = new HashMap<>();

        map.put("businessId", businessId);

        List<Pos> posList =namedParameterJdbcTemplate.query(sql,map,new PosRowMapper());

        if (posList.size() > 0) {
            return posList.get(0);
        }
        return null;
    }

    @Override
    public Integer totalPos(PosQueryParams posQueryParams) {
        String sql = "SELECT count(*) FROM pos WHERE 1=1 ";

        Map<String, Object> map = new HashMap<>();

        if(posQueryParams.getStatusCategory() != null ) {
            sql = sql + " AND validDate = :validDate";
//            System.out.println(posQueryParams.getStatusCategory().toString());
            map.put("validDate" ,posQueryParams.getStatusCategory().toString());
        }

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public void updateStatus(Integer posId,Integer businessId,  PosRequest posRequest) {
        String sql = "UPDATE pos SET businessId = :businessId , validDate = :validDate,expiryDate = :expiryDate,  UUID =:UUID " +
                " WHERE posId = :posId ";

        //時間邏輯判斷
        Date now = new Date();
        Timestamp ts = new Timestamp(now.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        if(posRequest.getValidDate().name() == "試用期"){
            cal.add(Calendar.DAY_OF_WEEK, 14);
        }
        if(posRequest.getValidDate().name() == "開通中"){
            cal.add(Calendar.MONTH, 12);
        }

        ts = new Timestamp(cal.getTime().getTime());

        Map<String, Object> map = new HashMap<>();

        map.put("businessId", businessId);
        map.put("validDate",posRequest.getValidDate().name());
        map.put("expiryDate", ts);
        map.put("UUID" , posRequest.getUUID());
        map.put("posId" , posId);

        namedParameterJdbcTemplate.update(sql, map );

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
        map.put("validDate", posRequest.getValidDate().name());

        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        map.put("expiryDate", timestamp);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int posId = keyHolder.getKey().intValue();

        return posId;
    }

    //後台用
    @Override
    public List<Pos> getAllPosList(PosQueryParams posQueryParams) {
        String sql = "SELECT * FROM pos WHERE 1=1 ";
        Map<String, Object> map = new HashMap<>();

        if (posQueryParams.getStatusCategory() != null) {
            sql = sql + " AND validDate = :category";
            map.put("category", posQueryParams.getStatusCategory().name());
        }

        // 排序
        sql = sql + " ORDER BY " + posQueryParams.getOrderBy() + " " + posQueryParams.getSorting();

        sql = sql + " LIMIT :limit OFFSET :offset";

        map.put("limit", posQueryParams.getLimit());
        map.put("offset", posQueryParams.getOffset());

        return namedParameterJdbcTemplate.query(sql,map , new PosRowMapper());
    }
}
