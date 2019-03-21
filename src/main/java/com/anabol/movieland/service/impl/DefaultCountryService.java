package com.anabol.movieland.service.impl;

import com.anabol.movieland.dao.CountryDao;
import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultCountryService implements CountryService {
    private final CountryDao countryDao;

    @Override
    public void enrich(Movie movie) {
        movie.setCountries(countryDao.getByMovieId(movie.getId()));
    }
}
