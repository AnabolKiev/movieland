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
}
