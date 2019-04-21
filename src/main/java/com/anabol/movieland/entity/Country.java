package com.anabol.movieland.entity;

import lombok.*;

@Getter
@ToString
public class Country {

    private final int id;
    private String name;

    public Country(int id) {
        this.id = id;
    }

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
