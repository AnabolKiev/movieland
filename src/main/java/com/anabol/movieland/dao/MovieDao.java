package com.anabol.movieland.dao;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.web.utils.RequestParameters;

import java.util.List;

public interface MovieDao {
    List<Movie> getAll();

    List<Movie> getAll(RequestParameters requestParameters);

    List<Movie> getRandom(int limit);

    List<Movie> getByGenreId(int genreId);

    List<Movie> getByGenreId(int genreId, RequestParameters requestParameters);

    Movie getById(int id);

    int add(Movie movie);

    void update(Movie movie);
}
