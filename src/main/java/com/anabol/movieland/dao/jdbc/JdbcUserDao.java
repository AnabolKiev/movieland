package com.anabol.movieland.dao.jdbc;

import com.anabol.movieland.dao.UserDao;
import com.anabol.movieland.dao.jdbc.mapper.UserMapper;
import com.anabol.movieland.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcUserDao implements UserDao {
    private static final UserMapper USER_MAPPER = new UserMapper();
    private static final String GET_BY_EMAIL_QUERY = "SELECT id, email, nickname, password FROM user WHERE email = ?";
    private final JdbcTemplate jdbcTemplate;

    @Override
    public User getByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(GET_BY_EMAIL_QUERY, USER_MAPPER, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
