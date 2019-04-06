package com.anabol.movieland.dao.jdbc;

import com.anabol.movieland.dao.MovieDao;
import com.anabol.movieland.dao.jdbc.mapper.MovieMapper;
import com.anabol.movieland.dao.jdbc.mapper.MovieMapperFull;
import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.web.utils.RequestParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.anabol.movieland.dao.jdbc.utils.QueryBuilder.addOrder;

@Repository
@RequiredArgsConstructor
public class JdbcMovieDao implements MovieDao {
    private static final MovieMapper MOVIE_MAPPER = new MovieMapper();
    private static final MovieMapperFull MOVIE_MAPPER_FULL = new MovieMapperFull();
    private static final String GET_ALL_QUERY = "SELECT id, nameRussian, nameNative, yearOfRelease, rating, price, " +
            "picturePath FROM movie";
    private static final String GET_RANDOM_QUERY = "SELECT id, nameRussian, nameNative, yearOfRelease, rating, price, " +
            "picturePath FROM movie ORDER BY RAND() LIMIT ?";
    private static final String GET_BY_GENRE = "SELECT id, nameRussian, nameNative, yearOfRelease, rating, price, " +
            "picturePath FROM movie m, movieGenre mg WHERE mg.movieId = m.id AND mg.genreId = ?";
    private static final String GET_BY_ID_QUERY = "SELECT id, nameRussian, nameNative, yearOfRelease, description, " +
            "rating, price, picturePath FROM movie WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO movie(nameRussian, nameNative, yearOfRelease, description, " +
            "price, picturePath) VALUES(:nameRussian, :nameNative, :yearOfRelease, :description, :price, :picturePath)";
    private static final String UPDATE_QUERY = "UPDATE movie SET nameRussian = IFNULL(:nameRussian, nameRussian), " +
            "nameNative = IFNULL(:nameNative, nameNative), yearOfRelease = IFNULL(:yearOfRelease, yearOfRelease), " +
            "description = IFNULL(:description, description), price = IF(:price = 0, price, :price), " +
            "picturePath = IFNULL(:picturePath, picturePath) WHERE id = :id";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    @Override
    public Movie getById(int id) {
        return jdbcTemplate.queryForObject(GET_BY_ID_QUERY, MOVIE_MAPPER_FULL, id);
    }

    @Override
    public int add(Movie movie) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nameRussian", movie.getNameRussian());
        parameterSource.addValue("nameNative", movie.getNameNative());
        parameterSource.addValue("description", movie.getDescription());
        parameterSource.addValue("yearOfRelease", movie.getYearOfRelease());
        parameterSource.addValue("price", movie.getPrice());
        parameterSource.addValue("picturePath", movie.getPicturePath());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT_QUERY, parameterSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(Movie movie) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", movie.getId());
        parameterSource.addValue("nameRussian", movie.getNameRussian());
        parameterSource.addValue("nameNative", movie.getNameNative());
        parameterSource.addValue("description", movie.getDescription());
        parameterSource.addValue("yearOfRelease", movie.getYearOfRelease());
        parameterSource.addValue("price", movie.getPrice());
        parameterSource.addValue("picturePath", movie.getPicturePath());
        namedParameterJdbcTemplate.update(UPDATE_QUERY, parameterSource);
    }
}
