package com.anabol.movieland.web.controller;

import com.anabol.movieland.entity.Genre;
import com.anabol.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("genre")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Genre> getAll() {
        return genreService.getAll();
    }
}
