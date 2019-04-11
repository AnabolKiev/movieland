package com.anabol.movieland.dao.jdbc;

import com.anabol.movieland.dao.CountryDao;
import com.anabol.movieland.dao.jdbc.mapper.CountryMapper;
import com.anabol.movieland.entity.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcCountryDao implements CountryDao {
    private static final CountryMapper COUNTRY_MAPPER = new CountryMapper();
    private static final String GET_BY_MOVIE_QUERY = "SELECT DISTINCT c.id, c.name FROM movieCountry mc, country c " +
            "WHERE mc.countryId = c.id AND mc.movieId = ?";
    private static final String INSERT_QUERY = "INSERT INTO movieCountry(movieId, countryId) VALUES (?, ?)";
    private static final String DELETE_BY_MOVIE_QUERY = "DELETE FROM movieCountry WHERE movieId = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Country> getByMovieId(int movieId) {
        return jdbcTemplate.query(GET_BY_MOVIE_QUERY, COUNTRY_MAPPER, movieId);
    }

    @Override
    public void add(int movieId, List<Country> countries) {
        jdbcTemplate.batchUpdate(INSERT_QUERY,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, movieId);
                        ps.setInt(2, countries.get(i).getId());
                    }

                    @Override
                    public int getBatchSize() {
                        return countries.size();
                    }
                });
    }

    @Override
    public void deleteByMovieId(int movieId) {
        jdbcTemplate.update(DELETE_BY_MOVIE_QUERY, movieId);
    }
}
