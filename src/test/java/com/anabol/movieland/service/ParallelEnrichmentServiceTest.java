package com.anabol.movieland.service;

import com.anabol.movieland.dao.CountryDao;
import com.anabol.movieland.dao.GenreDao;
import com.anabol.movieland.entity.Country;
import com.anabol.movieland.entity.Genre;
import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.impl.DefaultCountryService;
import com.anabol.movieland.service.impl.DefaultGenreService;
import com.anabol.movieland.service.impl.ParallelEnrichmentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ParallelEnrichmentServiceTest {

    private CountryDao countryDao = Mockito.mock(CountryDao.class);
    private CountryService countryService = new DefaultCountryService(countryDao);
    private GenreDao genreDao = Mockito.mock(GenreDao.class);
    private GenreService genreService = new DefaultGenreService(genreDao);
    private ParallelEnrichmentService enrichmentService = new ParallelEnrichmentService(countryService, genreService, Executors.newFixedThreadPool(2));

    @Before
    public void setUp() {
        enrichmentService.setEnrichmentTimeOut(2);
        when(countryDao.getByMovieId(0)).thenReturn(Collections.singletonList(new Country(1, "США")));
    }

    @Test
    public void testEnrichment() {
        when(genreDao.getByMovieId(0)).thenReturn(Collections.singletonList(new Genre(1, "Comedy")));

        Movie movie = new Movie();
        movie.setId(0);
        enrichmentService.enrich(movie);

        assertEquals("США", movie.getCountries().get(0).getName());
        assertEquals("Comedy", movie.getGenres().get(0).getName());
    }

    @Test
    public void testEnrichmentTimeout() {
        when(genreDao.getByMovieId(0)).thenAnswer(invocation -> {
            Thread.sleep(3000);
            return Collections.singletonList(new Genre(1, "Comedy"));
        });

        Movie movie = new Movie();
        movie.setId(0);
        enrichmentService.enrich(movie);

        assertEquals("США", movie.getCountries().get(0).getName());
        assertNull(movie.getGenres());
    }
}
