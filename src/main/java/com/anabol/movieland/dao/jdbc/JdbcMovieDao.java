package com.anabol.movieland.dao.jdbc;

import com.anabol.movieland.dao.MovieDao;
import com.anabol.movieland.dao.jdbc.mapper.MovieMapper;
import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.web.utils.RequestParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.anabol.movieland.dao.jdbc.utils.QueryBuilder.addOrder;

@Repository
@RequiredArgsConstructor
public class JdbcMovieDao implements MovieDao {
    private static final MovieMapper MOVIE_MAPPER = new MovieMapper();
    private static final String GET_ALL_QUERY = "SELECT id, nameRussian, nameNative, yearOfRelease, rating, price, " +
            "picturePath FROM movie";
    private static final String GET_RANDOM_QUERY = "SELECT id, nameRussian, nameNative, yearOfRelease, rating, price, " +
            "picturePath FROM movie ORDER BY RAND() LIMIT ?";
    private static final String GET_BY_GENRE = "SELECT id, nameRussian, nameNative, yearOfRelease, rating, price, " +
            "picturePath FROM movie m, movieGenre mg WHERE mg.movieId = m.id AND mg.genreId = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> getAll() {
        return jdbcTemplate.query(GET_ALL_QUERY, MOVIE_MAPPER);
    }

    @Override
    public List<Movie> getAll(RequestParameters requestParameters) {
        return jdbcTemplate.query(addOrder(GET_ALL_QUERY, requestParameters), MOVIE_MAPPER);
    }

    @Override
    public List<Movie> getRandom(int limit) {
        return jdbcTemplate.query(GET_RANDOM_QUERY, MOVIE_MAPPER, limit);
    }

    @Override
    public List<Movie> getByGenreId(int genreId) {
        return jdbcTemplate.query(GET_BY_GENRE, MOVIE_MAPPER, genreId);
    }

    @Override
    public List<Movie> getByGenreId(int genreId, RequestParameters requestParameters) {
        return jdbcTemplate.query(addOrder(GET_BY_GENRE, requestParameters), MOVIE_MAPPER, genreId);
    }

}
