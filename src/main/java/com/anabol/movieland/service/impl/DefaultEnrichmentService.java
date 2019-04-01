package com.anabol.movieland.service.impl;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.*;
import com.anabol.movieland.web.utils.Currency;
import com.anabol.movieland.web.utils.RequestParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultEnrichmentService implements EnrichmentService {
    private final CountryService countryService;
    private final GenreService genreService;
    private final ReviewService reviewService;

    @Override
    public void enrich(Movie movie) {
        countryService.enrich(movie);
        genreService.enrich(movie);
        reviewService.enrich(movie);
    }
}
