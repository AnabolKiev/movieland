package com.anabol.movieland.dao.cached.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDto {
    @JsonProperty("cc")
    private String currencyCode;
    private double rate;
}
