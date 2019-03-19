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
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
@Primary
@Slf4j
@RequiredArgsConstructor
public class CachedGenreDao implements GenreDao {
    private List<Genre> genres;

    private final GenreDao genreDao;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @PostConstruct
    @Scheduled(cron = "${genre.refreshCron}")
    void refreshCache() {
        try {
            lock.writeLock().lock();
            genres = genreDao.getAll();
            log.info("Genres cache updated, {} records extracted from database", genres.size());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Genre> getAll() {
        try {
            lock.readLock().lock();
            return new ArrayList<>(genres);
        } finally {
            lock.readLock().unlock();
        }
    }
}
