package com.anabol.movieland.service;

import com.anabol.movieland.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getByEmail(String email);
}
