package com.anabol.movieland.service;

import com.anabol.movieland.entity.Country;
import com.anabol.movieland.entity.Movie;

import java.util.List;

public interface CountryService {
    void enrich(Movie movie);
    void add(int movieId, List<Country> countries);
    void deleteByMovieId(int movieId);
}
