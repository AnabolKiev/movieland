package com.anabol.movieland.web.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public enum Currency {
    USD("USD"),
    EUR("EUR");

    private final String name;

    public static Currency getByName(String name) {
        Currency[] currencies = Currency.values();
        for (Currency currency : currencies) {
            if (currency.getName().equalsIgnoreCase(name)) {
                return currency;
            }
        }
        throw new IllegalArgumentException("No currency found for code " + name);
    }

    public static boolean contains(String name) {
        Currency[] currencies = Currency.values();
        for (Currency currency : currencies) {
            if (currency.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
