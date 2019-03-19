package com.anabol.movieland.web.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class RequestParameters {
    private final String attribute;
    private final SortDirection sortDirection;

    @Override // for Mockito
    public boolean equals(Object o) {
        RequestParameters requestParameters = RequestParameters.class.cast(o);
        return Objects.equals(attribute, requestParameters.attribute) &&
                sortDirection == requestParameters.sortDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute, sortDirection);
    }
}
