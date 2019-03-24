package com.anabol.movieland.service.impl;

import com.anabol.movieland.dao.MovieDao;
import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.*;
import com.anabol.movieland.web.utils.RequestParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {
    private final MovieDao movieDao;
    private final CountryService countryService;
    private final GenreService genreService;
    private final ReviewService reviewService;
    private final CurrencyService currencyService;

    @Value("${movie.randomLimit:3}")
    private int randomLimit;

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public List<Movie> getAll(RequestParameters requestParameters) {
        return movieDao.getAll(requestParameters);
    }

    @Override
    public List<Movie> getRandom() {
        return movieDao.getRandom(randomLimit);
    }

    @Override
    public List<Movie> getByGenreId(int genreId) {
        return movieDao.getByGenreId(genreId);
    }

    @Override
    public List<Movie> getByGenreId(int genreId, RequestParameters requestParameters) {
        return movieDao.getByGenreId(genreId, requestParameters);
    }

    @Override
    public Movie getById(int id) {
        Movie movie = movieDao.getById(id);
        countryService.enrich(movie);
        genreService.enrich(movie);
        reviewService.enrich(movie);
        log.info("Movie {} was extracted and enriched", movie);
        return movie;
    }

    @Override
    public Movie getById(int id, RequestParameters requestParameters) {
        Movie movie = getById(id);
        double rate = currencyService.getRate(requestParameters.getCurrency());
        movie.setPrice(Math.round(movie.getPrice()/rate*100)/100);
        log.info("Price was recalculated with rate {}", rate);
        return movie;
    }
}
