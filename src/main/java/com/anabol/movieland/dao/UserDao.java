package com.anabol.movieland.dao;

import com.anabol.movieland.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> getByEmail(String email);
}
