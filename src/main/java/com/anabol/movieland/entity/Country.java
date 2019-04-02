package com.anabol.movieland.entity;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Country {
    private int id;
    private String name;

    public Country(int id) {
        this.id = id;
    }
}
