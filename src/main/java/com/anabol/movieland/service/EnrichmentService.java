package com.anabol.movieland.service;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.web.utils.RequestParameters;

public interface EnrichmentService {
    void enrich(Movie movie);
    void saveDetails(Movie movie);
}
