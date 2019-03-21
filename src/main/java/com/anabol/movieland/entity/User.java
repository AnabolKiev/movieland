package com.anabol.movieland.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class User {
    private final int id;
    private final String nickName;
}
