package com.anabol.movieland.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Review {
    private int id;
    private User user;
    private String text;
}
