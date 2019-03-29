package com.anabol.movieland.web.auth;

import com.anabol.movieland.entity.User;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Session {
    @JsonProperty("uuid")
    private final String token;
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="nickName")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("nickname")
    private final User user;
    @JsonIgnore
    private final LocalDateTime expireDate;
}
