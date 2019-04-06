package com.anabol.movieland.web.controller;

import com.anabol.movieland.entity.UserRole;
import com.anabol.movieland.web.auth.annotation.Secured;
import com.anabol.movieland.web.utils.*;
import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.MovieService;
import com.anabol.movieland.web.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @JsonView(Views.MovieShort.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAll(@RequestParam(value = "rating", required = false) SortDirection ratingSortDirection,
                              @RequestParam(value = "price", required = false) SortDirection priceSortDirection) {
        RequestParameters requestParameters = createRequestParameters(ratingSortDirection, priceSortDirection);
        if (requestParameters != null) {
            return movieService.getAll(requestParameters);
        }
        return movieService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured(UserRole.ADMIN)
    public void add(@RequestBody Movie movie) {
        movieService.add(movie);
    }

    @JsonView(Views.MovieShort.class)
    @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getRandom() {
        return movieService.getRandom();
    }

    @JsonView(Views.MovieShort.class)
    @GetMapping(value = "/genre/{genreId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getByGenre(@PathVariable("genreId") int genreId,
                                  @RequestParam(value = "rating", required = false) SortDirection ratingSortDirection,
                                  @RequestParam(value = "price", required = false) SortDirection priceSortDirection) {
        RequestParameters requestParameters = createRequestParameters(ratingSortDirection, priceSortDirection);
        if (requestParameters != null) {
            return movieService.getByGenreId(genreId, requestParameters);
        }
        return movieService.getByGenreId(genreId);
    }

    @GetMapping(value = "/{movieId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Movie getById(@PathVariable int movieId, @RequestParam(value = "currency", required = false, defaultValue = "UAH") Currency currency) {
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.setCurrency(currency);
        return movieService.getById(movieId, requestParameters);
    }

    @PutMapping(value = "/{movieId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured(UserRole.ADMIN)
    public void update(@PathVariable int movieId, @RequestBody Movie movie) {
        movie.setId(movieId);
        movieService.update(movie);
    }

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(SortDirection.class, new SortDirectionConverter());
        webdataBinder.registerCustomEditor(Currency.class, new CurrencyConverter());
    }

    RequestParameters createRequestParameters(SortDirection ratingSortDirection, SortDirection priceSortDirection) {
        if (ratingSortDirection != null) {
            if (ratingSortDirection == SortDirection.DESC) {
                RequestParameters requestParameters = new RequestParameters();
                requestParameters.setAttribute("rating");
                requestParameters.setSortDirection(ratingSortDirection);
                return requestParameters;
            } else {
                throw new IllegalArgumentException("This sorting order doesn't supported for the attribute");
            }
        }
        if (priceSortDirection != null) {
            RequestParameters requestParameters = new RequestParameters();
            requestParameters.setAttribute("price");
            requestParameters.setSortDirection(priceSortDirection);
            return requestParameters;
        }
        return null;
    }
}
