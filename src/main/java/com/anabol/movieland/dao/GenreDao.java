package com.anabol.movieland.dao;

import com.anabol.movieland.entity.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();
    List<Genre> getByMovieId(int movieId);
    void add(int movieId, List<Genre> genres);
    void deleteByMovieId(int movieId);
}
