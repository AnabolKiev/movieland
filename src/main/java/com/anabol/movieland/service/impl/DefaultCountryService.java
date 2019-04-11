package com.anabol.movieland.service.impl;

import com.anabol.movieland.dao.CountryDao;
import com.anabol.movieland.entity.Country;
import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCountryService implements CountryService {
    private final CountryDao countryDao;

    @Override
    public void enrich(Movie movie) {
        movie.setCountries(countryDao.getByMovieId(movie.getId()));
    }

    @Override
    public void add(int movieId, List<Country> countries) {
        countryDao.add(movieId, countries);
    }

    @Override
    public void deleteByMovieId(int movieId) {
        countryDao.deleteByMovieId(movieId);
    }
}
