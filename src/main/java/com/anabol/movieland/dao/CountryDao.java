package com.anabol.movieland.dao;

import com.anabol.movieland.entity.Country;

import java.util.List;

public interface CountryDao {
    List<Country> getByMovieId(int movieId);
    void add(int movieId, int countryId);
}
