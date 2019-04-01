package com.anabol.movieland.service;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.entity.Review;

public interface ReviewService {
    void enrich(Movie movie);
    void save(Review review);
}
