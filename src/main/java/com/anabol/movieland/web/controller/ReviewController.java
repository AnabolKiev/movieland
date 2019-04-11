package com.anabol.movieland.web.controller;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.entity.Review;
import com.anabol.movieland.entity.UserRole;
import com.anabol.movieland.service.ReviewService;
import com.anabol.movieland.web.auth.UserHolder;
import com.anabol.movieland.web.auth.annotation.Secured;
import com.anabol.movieland.web.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = "/review", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({UserRole.USER, UserRole.ADMIN})
    public void addReview(@RequestBody ReviewDto reviewDto) {
        Review review = new Review();
        Movie movie = new Movie();
        movie.setId(reviewDto.getMovieId());
        review.setMovie(movie);
        review.setText(reviewDto.getText());
        review.setUser(UserHolder.getCurrentUser());

        reviewService.save(review);
    }
}
