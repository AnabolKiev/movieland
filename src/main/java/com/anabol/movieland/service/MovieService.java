package com.anabol.movieland.service;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.web.utils.RequestParameters;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> getAll(RequestParameters requestParameters);

    List<Movie> getRandom();

    List<Movie> getByGenreId(int genreId);

    List<Movie> getByGenreId(int genreId, RequestParameters requestParameters);

    Movie getById(int id, RequestParameters requestParameters);

    void add(Movie movie);

    void update(Movie movie);

}
