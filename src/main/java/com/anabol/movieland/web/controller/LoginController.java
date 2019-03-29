package com.anabol.movieland.web.controller;

import com.anabol.movieland.service.SecurityService;
import com.anabol.movieland.web.auth.LoginForm;
import com.anabol.movieland.web.auth.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final SecurityService securityService;

    @PostMapping(value = "login",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Session login(@RequestBody LoginForm loginForm) {
        Optional<Session> session = securityService.login(loginForm.getEmail(), loginForm.getPassword());
        if (session.isPresent()) {
            return session.get();
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong combination of login/password");
    }

    @DeleteMapping(value = "logout")
    public void logout(@RequestHeader(name = "uuid") String token) {
        securityService.removeByToken(token);
    }
}
