package com.anabol.movieland.web.auth;

import com.anabol.movieland.entity.User;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Session {
    @JsonProperty("uuid")
    private String token;
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="nickName")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("nickname")
    private User user;
    @JsonIgnore
    private LocalDateTime expireDate;
}
