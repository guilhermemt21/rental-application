package com.rental.server.core.property;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyMapper implements RowMapper<Property> {


    @Override
    public Property map(ResultSet rs, StatementContext ctx) throws SQLException {
        return Property.builder()
                .id(rs.getLong("id"))
                .latitude(rs.getDouble("latitude"))
                .longitude(rs.getDouble("longitude"))
                .type(rs.getString("type"))
                .address(rs.getString("address"))
                .neighbourhood(rs.getString("neighbourhood"))
                .size(rs.getInt("size"))
                .bedrooms(rs.getInt("bedrooms"))
                .price(rs.getBigDecimal("price"))
                .urls(new PropertyUrls(rs.getString("url_thumb")))
                .build();
    }
}
