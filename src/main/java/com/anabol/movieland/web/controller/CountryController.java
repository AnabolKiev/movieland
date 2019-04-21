package com.anabol.movieland.web.controller;

import com.anabol.movieland.entity.Country;
import com.anabol.movieland.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Country> getAll() {
        return countryService.getAll();
    }
}
