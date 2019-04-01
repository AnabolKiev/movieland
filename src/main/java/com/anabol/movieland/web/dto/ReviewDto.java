package com.anabol.movieland.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReviewDto {
    private int movieId;
    private String text;
}
