package com.anabol.movieland.service;

import com.anabol.movieland.entity.Movie;

public interface MovieUpdateDetailsService {
    void saveMovieDetails(Movie movie);
    void deleteMovieDetails(int movieId);
}
