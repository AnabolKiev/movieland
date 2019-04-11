package com.anabol.movieland.service.impl;

import com.anabol.movieland.entity.User;
import com.anabol.movieland.entity.UserRole;
import com.anabol.movieland.service.SecurityService;
import com.anabol.movieland.service.UserService;
import com.anabol.movieland.web.auth.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultSecurityService implements SecurityService {
    private final UserService userService;
    private int sessionLengthHours;
    private ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Optional<Session> login(String email, String password) {
        Optional<User> userOptional = userService.getByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (encoder.matches(password, user.getPassword())) {
                String token = UUID.randomUUID().toString();
                Session session = new Session(token, user, LocalDateTime.now().plusHours(sessionLengthHours));
                sessions.put(token, session);
                log.info("New session created for user {}", user.getNickName());
                return Optional.of(session);
            }
        }
        return Optional.empty();
    }

    @Override
    public void removeByToken(String token) {
        if (sessions.remove(token) != null) {
            log.info("Session with token {} was removed", token);
        }
    }

    @Override
    public Optional<Session> getByToken(String token) {
        return Optional.ofNullable(sessions.get(token));
    }

    @Scheduled(fixedDelayString = "${session.cleanMillis:300000}", initialDelayString = "${session.cleanMillis:300000}")
    private void cleanUpSessions() {
        sessions.entrySet().removeIf(session -> session.getValue().getExpireDate().isBefore(LocalDateTime.now()));
    }

    @Value("${session.expirationHours:2}")
    public void setSessionLengthHours(int sessionLengthHours) {
        this.sessionLengthHours = sessionLengthHours;
    }

    @Override
    public Optional<User> validateByTokenAndRole(String token, List<UserRole> expectedRoles) {
        if (token != null) {
            Optional<Session> sessionOptional = getByToken(token);
            if (sessionOptional.isPresent() && expectedRoles.contains(sessionOptional.get().getUser())) {
                return Optional.of(sessionOptional.get().getUser());
            }
        }
        return Optional.empty();
    }
}
