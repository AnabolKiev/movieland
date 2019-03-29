package com.anabol.movieland.service;

import com.anabol.movieland.web.auth.Session;

import java.util.Optional;

public interface SecurityService {
    Optional<Session> login(String email, String password);
    void removeByToken(String token);
}
