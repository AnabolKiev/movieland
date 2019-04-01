package com.anabol.movieland.dao.jdbc;

import com.anabol.movieland.dao.ReviewDao;
import com.anabol.movieland.dao.jdbc.mapper.ReviewMapper;
import com.anabol.movieland.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcReviewDao implements ReviewDao {
    private static final ReviewMapper REVIEW_MAPPER = new ReviewMapper();
    private static final String GET_BY_MOVIE_QUERY = "SELECT r.id, userid, nickname, text FROM review r, user u " +
            "WHERE u.id = r.userId AND r.movieId = ?";
    private static final String INSERT_QUERY = "INSERT INTO review (movieId, userId, text) VALUES (?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Review> getByMovieId(int movieId) {
        return jdbcTemplate.query(GET_BY_MOVIE_QUERY, REVIEW_MAPPER, movieId);
    }

    @Override
    public void save(Review review) {
        jdbcTemplate.update(INSERT_QUERY, review.getMovie().getId(), review.getUser().getId(), review.getText());
    }
}
