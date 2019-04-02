package com.anabol.movieland.dao.jdbc.mapper;

import com.anabol.movieland.entity.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country counrty = new Country();
        counrty.setId(resultSet.getInt("id"));
        counrty.setName(resultSet.getString("name"));
        return counrty;
    }
}
