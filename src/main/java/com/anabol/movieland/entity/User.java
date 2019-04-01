package com.anabol.movieland.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Setter
@Getter
@ToString
public class User {
    private int id;
    @JsonIgnore
    private String email;
    private String nickName;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private UserRole role;
}
