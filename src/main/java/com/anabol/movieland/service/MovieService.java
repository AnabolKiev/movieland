package com.anabol.movieland.service;

import com.anabol.movieland.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> getRandom();

    List<Movie> getByGenreId(int genreId);
}
