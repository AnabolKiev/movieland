package com.anabol.movieland.dao.jdbc.mapper;

import com.anabol.movieland.entity.User;
import com.anabol.movieland.entity.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setNickName(resultSet.getString("nickname"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(UserRole.getByName(resultSet.getString("role")));
        return user;
    }
}
