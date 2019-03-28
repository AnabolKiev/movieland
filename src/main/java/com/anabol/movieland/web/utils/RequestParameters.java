package com.anabol.movieland.web.utils;

import lombok.*;

@Setter
@Getter
@ToString
public class RequestParameters {
    private String attribute;
    private SortDirection sortDirection;
    private Currency currency;

    @Override // for Mockito
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestParameters)) return false;

        RequestParameters that = (RequestParameters) o;

        if (attribute != null ? !attribute.equals(that.attribute) : that.attribute != null) return false;
        if (sortDirection != that.sortDirection) return false;
        return currency == that.currency;

    }

    @Override
    public int hashCode() {
        int result = attribute != null ? attribute.hashCode() : 0;
        result = 31 * result + (sortDirection != null ? sortDirection.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
