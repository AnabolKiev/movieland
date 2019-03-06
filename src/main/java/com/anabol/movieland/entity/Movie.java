package com.anabol.movieland.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Movie {
    private int id;
    private String nameRussian;
    private String nameNative;
    private String description;
    private String yearOfRelease;
    private double rating;
    private double price;
    private String picturePath;
}
