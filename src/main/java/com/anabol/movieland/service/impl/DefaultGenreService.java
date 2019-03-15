package com.anabol.movieland.service.impl;

import com.anabol.movieland.dao.GenreDao;
import com.anabol.movieland.entity.Genre;
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
}
