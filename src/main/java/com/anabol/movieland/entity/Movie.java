package com.anabol.movieland.entity;

import com.anabol.movieland.web.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class Movie {
    @JsonView(Views.MovieShort.class)
    private int id;
    @JsonView(Views.MovieShort.class)
    private String nameRussian;
    @JsonView(Views.MovieShort.class)
    private String nameNative;
    private String description;
    @JsonView(Views.MovieShort.class)
    private String yearOfRelease;
    @JsonView(Views.MovieShort.class)
    private double rating;
    @JsonView(Views.MovieShort.class)
    private double price;
    @JsonView(Views.MovieShort.class)
    private String picturePath;
    private List<Country> countries;
    private List<Genre> genres;
    private List<Review> reviews;
}
