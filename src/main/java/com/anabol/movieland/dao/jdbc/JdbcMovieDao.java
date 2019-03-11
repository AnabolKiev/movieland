package com.anabol.movieland.dao.jdbc;

import com.anabol.movieland.dao.MovieDao;
import com.anabol.movieland.dao.jdbc.mapper.MovieMapper;
import com.anabol.movieland.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcMovieDao implements MovieDao {
    private static final MovieMapper MOVIE_MAPPER = new MovieMapper();
    private static final String GET_ALL_QUERY = "SELECT id, nameRussian, nameNative, yearOfRelease, rating, price, " +
            "picturePath FROM movie";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Movie> getAll() {
        return namedParameterJdbcTemplate.query(GET_ALL_QUERY, MOVIE_MAPPER);
    }
}
