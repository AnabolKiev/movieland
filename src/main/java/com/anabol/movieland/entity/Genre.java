package com.anabol.movieland.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Genre {
    private final int id;
    private final String name;
}
