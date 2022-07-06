package com.eeit45.champion.vegetarian.rowmapper.customer;

import com.eeit45.champion.vegetarian.model.customer.Pos;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class PosRowMapper  implements RowMapper<Pos> {

    @Override
    public Pos mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pos pos = new Pos();

        pos.setPosId(rs.getInt("posId"));
        pos.setBusinessId(rs.getInt("businessId"));
        pos.setValidDate(rs.getString("validDate"));

        Timestamp expiryDate = rs.getTimestamp("expiryDate");
        pos.setExpiryDate(expiryDate);

        pos.setVisitors(rs.getInt("visitors"));
        pos.setTurnOver(rs.getInt("turnOver"));

        Timestamp startDate = rs.getTimestamp("startDate");
        pos.setStartDate(startDate);

        Timestamp lastUsingDate = rs.getTimestamp("lastUsingDate");
        pos.setLastUsingDate(lastUsingDate);

        return pos;
    }
}
