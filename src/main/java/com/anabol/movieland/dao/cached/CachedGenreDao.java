package com.anabol.movieland.dao.cached;

import com.anabol.movieland.dao.GenreDao;
import com.anabol.movieland.entity.Genre;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
@Slf4j
@RequiredArgsConstructor
public class CachedGenreDao implements GenreDao{
    private List<Genre> genres;

    private final GenreDao genreDao;

    @PostConstruct
    @Scheduled(cron = "${genre.refreshCron}")
    private void initGenres() {
        genres = genreDao.getAll();
        log.info("Genres cache updated, " + genres.size() + " records extracted from database");
    }

    @Override
    public List<Genre> getAll() {
        if (genres == null) {
            initGenres();
        }
        return new ArrayList<>(genres);
    }
}
