package com.anabol.movieland.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Review {
    private int id;
    private User user;
    private String text;
    @JsonIgnore
    private Movie movie;
}
