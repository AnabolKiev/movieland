package com.anabol.movieland.dao.jdbc;

import com.anabol.movieland.dao.GenreDao;
import com.anabol.movieland.dao.jdbc.mapper.GenreMapper;
import com.anabol.movieland.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcGenreDao implements GenreDao{
    private static final GenreMapper GENRE_MAPPER = new GenreMapper();
    private static final String GET_ALL_QUERY = "SELECT id, name FROM genre";
    private static final String GET_BY_MOVIE_QUERY = "SELECT id, name from genre g, movieGenre mg " +
            "WHERE g.id = mg.genreId AND mg.movieId = ?";
    private static final String INSERT_QUERY = "INSERT INTO movieGenre(movieId, genreId) VALUES (?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> getAll() {
        return jdbcTemplate.query(GET_ALL_QUERY, GENRE_MAPPER);
    }

    @Override
    public List<Genre> getByMovieId(int movieId) {
        return jdbcTemplate.query(GET_BY_MOVIE_QUERY, GENRE_MAPPER, movieId);
    }

    @Override
    public void add(int movieId, int genreId) {
        jdbcTemplate.update(INSERT_QUERY, movieId, genreId);
    }
}
