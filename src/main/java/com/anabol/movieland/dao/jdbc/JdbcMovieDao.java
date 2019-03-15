package com.anabol.movieland.dao.jdbc;

import com.anabol.movieland.dao.MovieDao;
import com.anabol.movieland.dao.jdbc.mapper.MovieMapper;
import com.anabol.movieland.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcMovieDao implements MovieDao {
    private static final MovieMapper MOVIE_MAPPER = new MovieMapper();
    private static final String GET_ALL_QUERY = "SELECT id, nameRussian, nameNative, yearOfRelease, rating, price, " +
            "picturePath FROM movie";
    private static final String GET_RANDOM_QUERY = "SELECT id, nameRussian, nameNative, yearOfRelease, rating, price, " +
            "picturePath FROM movie ORDER BY RAND() LIMIT ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> getAll() {
        return jdbcTemplate.query(GET_ALL_QUERY, MOVIE_MAPPER);
    }

    @Override
    public List<Movie> getRandom(int limit) {
        return jdbcTemplate.query(GET_RANDOM_QUERY, MOVIE_MAPPER, limit);
    }
}
