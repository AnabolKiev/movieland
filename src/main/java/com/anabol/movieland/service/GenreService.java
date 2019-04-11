package com.anabol.movieland.service;

import com.anabol.movieland.entity.Genre;
import com.anabol.movieland.entity.Movie;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    void enrich(Movie movie);
    void add(int movieId, int genreId);
    void deleteByMovieId(int movieId);
}
