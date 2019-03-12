package com.anabol.movieland.dao;

import com.anabol.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> getAll();

    List<Movie> getRandom(int limit);
}
