package com.anabol.movieland.dao;

import com.anabol.movieland.entity.Country;

import java.util.List;

public interface CountryDao {
    List<Country> getAll();
    List<Country> getByMovieId(int movieId);
    void add(int movieId, List<Country> countries);
    void deleteByMovieId(int movieId);
}
