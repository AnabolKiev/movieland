package com.anabol.movieland.service;

import com.anabol.movieland.entity.User;
import com.anabol.movieland.entity.UserRole;
import com.anabol.movieland.web.auth.Session;

import java.util.List;
import java.util.Optional;

public interface SecurityService {
    Optional<Session> login(String email, String password);
    void removeByToken(String token);
    Optional<Session> getByToken(String token);
    Optional<User> validateByTokenAndRole(String token, List<UserRole> expectedRoles);
}
