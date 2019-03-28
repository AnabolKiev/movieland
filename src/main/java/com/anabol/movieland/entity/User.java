package com.anabol.movieland.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

@Setter
@Getter
@ToString
public class User {
    private int id;
    private String email;
    @JsonValue
    private String nickName;
    private String password;
}
