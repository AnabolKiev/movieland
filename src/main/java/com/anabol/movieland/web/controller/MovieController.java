package com.anabol.movieland.web.controller;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.web.utils.RequestParameters;
import com.anabol.movieland.web.utils.SortDirection;
import com.anabol.movieland.web.utils.SortDirectionConverter;
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
    public Movie getById(@PathVariable int movieId) {
        return movieService.getById(movieId);
    }

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(SortDirection.class, new SortDirectionConverter());
    }

    RequestParameters createRequestParameters(SortDirection ratingSortDirection, SortDirection priceSortDirection) {
        if (ratingSortDirection != null) {
            if (ratingSortDirection == SortDirection.DESC) {
                return new RequestParameters("rating", ratingSortDirection);
            } else {
                throw new IllegalArgumentException("This sorting order doesn't supported for the attribute");
            }
        }
        if (priceSortDirection != null) {
            return new RequestParameters("price", priceSortDirection);
        }
        return null;
    }
}
