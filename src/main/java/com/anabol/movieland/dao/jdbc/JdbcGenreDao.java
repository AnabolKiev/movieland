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

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> getAll() {
        return jdbcTemplate.query(GET_ALL_QUERY, GENRE_MAPPER);
    }
}
