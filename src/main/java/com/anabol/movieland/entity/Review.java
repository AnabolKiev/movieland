package com.anabol.movieland.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Review {
    private final int id;
    private final User user;
    private final String text;
}
