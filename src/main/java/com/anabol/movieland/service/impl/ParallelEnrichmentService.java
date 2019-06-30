package com.anabol.movieland.service.impl;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.CountryService;
import com.anabol.movieland.service.EnrichmentService;
import com.anabol.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class ParallelEnrichmentService implements EnrichmentService {
    private final CountryService countryService;
    private final GenreService genreService;
    private final ExecutorService executorService;

    private int enrichmentTimeOut;

    @Override
    public void enrich(Movie movie) {
        Runnable enrichCountry = () -> countryService.enrich(movie);
        Runnable enrichGenre = () -> genreService.enrich(movie);
        List<Callable<Void>> tasks = new ArrayList<>();
        tasks.add(Executors.callable(enrichCountry, null));
        tasks.add(Executors.callable(enrichGenre, null));

        try {
            executorService.invokeAll(tasks, enrichmentTimeOut, TimeUnit.SECONDS);
            log.info("Parallel enrichment was successful");
        } catch (InterruptedException e) {
            log.error("Parallel enrichment was interrupted", e);
        }

    }

    @Value("${movie.enrichment.timeoutSeconds:5}")
    public void setEnrichmentTimeOut(int enrichmentTimeOut) {
        this.enrichmentTimeOut = enrichmentTimeOut;
    }
}
