package com.eeit45.champion.vegetarian.rowmapper.customer;

import com.eeit45.champion.vegetarian.model.customer.PosBusiness;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PosBusinessRowMapper implements RowMapper<PosBusiness> {

    @Override
    public PosBusiness mapRow(ResultSet rs, int rowNum) throws SQLException {
        PosBusiness posBusiness = new PosBusiness();

        posBusiness.setPosBusinessId(rs.getInt("posBusinessId"));
        posBusiness.setPosId(rs.getInt("posId"));
        posBusiness.setBusinessId(rs.getInt("businessId"));
        posBusiness.setVisitors(rs.getInt("visitors"));
        posBusiness.setTurnOver(rs.getInt("turnOver"));
        posBusiness.setBusinessName(rs.getString("businessName"));

        return posBusiness;
    }
}
