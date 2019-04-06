package com.anabol.movieland.service.impl;

import com.anabol.movieland.dao.GenreDao;
import com.anabol.movieland.entity.Genre;
import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService{
    private final GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public void enrich(Movie movie) {
        movie.setGenres(genreDao.getByMovieId(movie.getId()));
    }

    @Override
    public void add(int movieId, int genreId) {
        genreDao.add(movieId, genreId);
    }

    @Override
    public void deleteByMovieId(int movieId) {
        genreDao.deleteByMovieId(movieId);
    }
}
