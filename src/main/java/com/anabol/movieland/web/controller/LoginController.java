package com.anabol.movieland.web.controller;

import com.anabol.movieland.service.SecurityService;
import com.anabol.movieland.web.auth.LoginForm;
import com.anabol.movieland.web.auth.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final SecurityService securityService;

    @PostMapping(value = "login",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Session login(@RequestBody LoginForm loginForm) {
        Session session = securityService.login(loginForm.getEmail(), loginForm.getPassword());
        if (session != null) {
            return session;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong combination of login/password");
    }

    @DeleteMapping(value = "logout")
    public void logout(@RequestHeader(name = "uuid", required = false) String token) {
        if (token != null) {
            securityService.removeByToken(token);
        }
    }
}
