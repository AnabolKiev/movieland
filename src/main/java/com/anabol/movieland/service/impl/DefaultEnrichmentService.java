package com.anabol.movieland.service.impl;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultEnrichmentService implements EnrichmentService {
    private final CountryService countryService;
    private final GenreService genreService;

    @Override
    public void enrich(Movie movie) {
        countryService.enrich(movie);
        genreService.enrich(movie);
    }

    @Override
    public void saveDetails(Movie movie) {
        movie.getCountries().stream().forEach(country -> countryService.add(movie.getId(), country.getId()));
        movie.getGenres().stream().forEach(genre -> genreService.add(movie.getId(), genre.getId()));
    }

    @Override
    public void deleteDetails(int movieId) {
        countryService.deleteByMovieId(movieId);
        genreService.deleteByMovieId(movieId);
    }
}
