package com.anabol.movieland.service.impl;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.CountryService;
import com.anabol.movieland.service.GenreService;
import com.anabol.movieland.service.MovieUpdateDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultMovieUpdateDetailsService implements MovieUpdateDetailsService {
    private final CountryService countryService;
    private final GenreService genreService;

    @Override
    public void saveMovieDetails(Movie movie) {
        countryService.add(movie.getId(), movie.getCountries());
        genreService.add(movie.getId(), movie.getGenres());
    }

    @Override
    public void deleteMovieDetails(int movieId) {
        countryService.deleteByMovieId(movieId);
        genreService.deleteByMovieId(movieId);
    }
}