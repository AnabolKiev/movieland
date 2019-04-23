package com.anabol.movieland.dao.cached;

import com.anabol.movieland.dao.CountryDao;
import com.anabol.movieland.entity.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@Primary
@RequiredArgsConstructor
@Slf4j
public class CachedCountryDao implements CountryDao {

    private final CountryDao countryDao;
    private List<Country> countries;

    @PostConstruct
    @Scheduled(cron = "${country.refreshCron}")
    void refreshCache() {
        countries = new CopyOnWriteArrayList<>(countryDao.getAll());
        log.info("Country cache updated, {} records extracted from database", countries.size());
    }

    @Override
    public List<Country> getAll() {
        return new ArrayList(countries);
    }

    @Override
    public List<Country> getByMovieId(int movieId) {
        return countryDao.getByMovieId(movieId);
    }

    @Override
    public void add(int movieId, List<Country> countries) {
        countryDao.add(movieId, countries);
    }

    @Override
    public void deleteByMovieId(int movieId) {
        countryDao.deleteByMovieId(movieId);
    }
}
