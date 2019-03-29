package com.anabol.movieland.service;

import com.anabol.movieland.web.auth.Session;

public interface SecurityService {
    Session login(String email, String password);
    void removeByToken(String token);
}
