package com.anabol.movieland.web.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

@Getter
@ToString
@RequiredArgsConstructor
public enum Currency {
    UAH("UAH"),
    USD("USD"),
    EUR("EUR");

    private final String name;

    public static Currency getByName(String name) {
        return Arrays.stream(Currency.values())
                .filter(currency -> currency.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No currency found for code " + name));
    }

    public static boolean contains(String name) {
        return Arrays.stream(Currency.values())
                .anyMatch(currency -> currency.getName().equalsIgnoreCase(name));
    }

}
