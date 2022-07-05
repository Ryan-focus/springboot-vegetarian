package com.eeit45.champion.vegetarian.rowmapper.customer;

import com.eeit45.champion.vegetarian.model.customer.Reserve;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ReserveRowMapper implements RowMapper<Reserve> {
    @Override
    public Reserve mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reserve reserve = new Reserve();

        reserve.setReserveId(rs.getInt("reserveId"));

        Timestamp reserveDate = rs.getTimestamp("reserveDateTime");
        reserve.setReserveDateTime(reserveDate);

        reserve.setAdult(rs.getInt("adult"));
        reserve.setChild(rs.getInt("child"));
        reserve.setBaby(rs.getInt("baby"));

        Timestamp reserveTime = rs.getTimestamp("reserveTime");
        reserve.setReserveTime(reserveTime);

        reserve.setRestaurantId(rs.getInt("restaurantId"));

        reserve.setBusinessId(rs.getInt("restaurantId"));

        reserve.setUserId(rs.getInt("userId"));

        return reserve;
    }
}
