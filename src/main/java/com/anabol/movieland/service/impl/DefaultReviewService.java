package com.anabol.movieland.service.impl;

import com.anabol.movieland.dao.ReviewDao;
import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultReviewService implements ReviewService {
    private final ReviewDao reviewDao;

    @Override
    public void enrich(Movie movie) {
        movie.setReviews(reviewDao.getByMovieId(movie.getId()));
    }
}
