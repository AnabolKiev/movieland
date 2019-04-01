package com.anabol.movieland.dao;

import com.anabol.movieland.entity.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> getByMovieId(int movieId);
    void save(Review review);
}
