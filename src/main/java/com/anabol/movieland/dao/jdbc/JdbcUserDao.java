package com.anabol.movieland.dao.jdbc;

import com.anabol.movieland.dao.UserDao;
import com.anabol.movieland.dao.jdbc.mapper.UserMapper;
import com.anabol.movieland.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcUserDao implements UserDao {
    private static final UserMapper USER_MAPPER = new UserMapper();
    private static final String GET_BY_EMAIL_QUERY = "SELECT id, email, nickname, password, role FROM user WHERE email = ?";
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> getByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(GET_BY_EMAIL_QUERY, USER_MAPPER, email));
        } catch (EmptyResultDataAccessException e) {
            log.info("User with email {} requested, but not found in database", email);
            return Optional.empty();
        }
    }
}
