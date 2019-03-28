package com.anabol.movieland.dao.jdbc.mapper;

import com.anabol.movieland.entity.Review;
import com.anabol.movieland.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("userId"));
        user.setNickName(resultSet.getString("nickname"));
        Review review = new Review();
        review.setId(resultSet.getInt("id"));
        review.setUser(user);
        review.setText(resultSet.getString("text"));
        return review;
    }
}
