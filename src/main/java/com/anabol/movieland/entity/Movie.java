package com.anabol.movieland.entity;

import com.anabol.movieland.web.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Movie {
    @JsonView(Views.Public.class)
    private int id;
    @JsonView(Views.Public.class)
    private String nameRussian;
    @JsonView(Views.Public.class)
    private String nameNative;
    private String description;
    @JsonView(Views.Public.class)
    private String yearOfRelease;
    @JsonView(Views.Public.class)
    private double rating;
    @JsonView(Views.Public.class)
    private double price;
    @JsonView(Views.Public.class)
    private String picturePath;
}
