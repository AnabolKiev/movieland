package com.anabol.movieland.web.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestParameters {
    private final String attribute;
    private final SortDirection sortDirection;

    @Override // for Mockito
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestParameters that = (RequestParameters) o;
        return Objects.equals(attribute, that.attribute) &&
                sortDirection == that.sortDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute, sortDirection);
    }
}
