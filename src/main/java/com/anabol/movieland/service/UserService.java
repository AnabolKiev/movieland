package com.anabol.movieland.service;

import com.anabol.movieland.entity.User;

public interface UserService {
    User getByEmail(String email);
}
