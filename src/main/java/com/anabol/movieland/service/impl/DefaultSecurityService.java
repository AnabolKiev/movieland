package com.anabol.movieland.service.impl;

import com.anabol.movieland.entity.User;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultSecurityService implements SecurityService {
    @Value("${session.expirationHours:2}")
    private int SESSION_LENGTH_HOURS;
    private final UserService userService;
    private List<Session> sessions = Collections.synchronizedList(new ArrayList<>());
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Session login(String email, String password) {
        User user = userService.getByEmail(email);
        if (user != null) {
            if (encoder.matches(password, user.getPassword())) {
                Session session = new Session();
                String token = UUID.randomUUID().toString();
                session.setToken(token);
                session.setUser(user);
                session.setExpireDate(LocalDateTime.now().plusHours(SESSION_LENGTH_HOURS));
                sessions.add(session);
                log.info("New session created for user {}", user.getNickName());
                return session;
            }
        }
        return null;
    }

    @Override
    public void removeByToken(String token) {
        if (sessions.removeIf(session -> session.getToken().equals(token))) {
            log.info("Session with token {} was removed", token);
        }
    }

    @Scheduled(fixedDelayString = "${session.cleanMillis:300000}", initialDelayString = "${session.cleanMillis:300000}")
    private void cleanUpSessions() {
        sessions.removeIf(session -> session.getExpireDate().isBefore(LocalDateTime.now()));
    }
}
