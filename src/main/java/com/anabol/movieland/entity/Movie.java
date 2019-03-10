package com.anabol.movieland.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Movie {
    public interface PublicView {}

    @JsonView(PublicView.class)
    private int id;
    @JsonView(PublicView.class)
    private String nameRussian;
    @JsonView(PublicView.class)
    private String nameNative;
    private String description;
    @JsonView(PublicView.class)
    private String yearOfRelease;
    @JsonView(PublicView.class)
    private double rating;
    @JsonView(PublicView.class)
    private double price;
    @JsonView(PublicView.class)
    private String picturePath;
}
