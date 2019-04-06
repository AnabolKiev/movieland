package com.anabol.movieland.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    private final String name;

    public static UserRole getByName(String name) {
        return Arrays.stream(UserRole.values())
                .filter(userRole -> userRole.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No user role found for code " + name));
    }
}
