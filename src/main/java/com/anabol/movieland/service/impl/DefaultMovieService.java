package com.anabol.movieland.service.impl;

import com.anabol.movieland.dao.MovieDao;
import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.*;
import com.anabol.movieland.web.utils.RequestParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {
    private final MovieDao movieDao;
    private final EnrichmentService enrichmentService;
    private final CurrencyService currencyService;

    private ConcurrentHashMap<Integer, SoftReference<Movie>> movieCache = new ConcurrentHashMap<>();

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
        if (movieCache.containsKey(id)) {
            Movie movie = movieCache.get(id).get();
            if (movie != null) {
                log.info("Movie {} was extracted from cache", movie);
                return movie;
            }
        }

        // key doesn't exist in cache or object in cache is null (cleared by GC)
        Movie movie = movieDao.getById(id);
        enrichmentService.enrich(movie);
        movieCache.put(id, new SoftReference<Movie>(movie));
        log.info("Movie {} was extracted from DB and enriched", movie);
        return movie;
    }

    @Override
    public Movie getById(int id, RequestParameters requestParameters) {
        Movie movie = new Movie(getById(id));
        currencyService.convert(movie, requestParameters);
        return movie;
    }
}
