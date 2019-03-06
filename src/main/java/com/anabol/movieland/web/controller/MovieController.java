package com.anabol.movieland.web.controller;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.MovieService;
import com.anabol.movieland.web.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @JsonView(Views.Public.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAll() {
        return movieService.getAll();
    }
}
