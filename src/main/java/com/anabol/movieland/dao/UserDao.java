package com.anabol.movieland.dao;

import com.anabol.movieland.entity.User;

public interface UserDao {
    User getByEmail(String email);
}
