package com.anabol.movieland.service;

import com.anabol.movieland.entity.Movie;

public interface EnrichmentService {
    void enrich(Movie movie);
    void saveDetails(Movie movie);
    void deleteDetails(int movieId);
}
