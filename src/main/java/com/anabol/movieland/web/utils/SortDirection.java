package com.anabol.movieland.web.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortDirection {
    ASC("ASC"),
    DESC("DESC");

    private final String name;

    public static SortDirection getByName(String name) {
        SortDirection[] sortDirections = SortDirection.values();
        for (SortDirection sortDirection : sortDirections) {
            if (sortDirection.getName().equalsIgnoreCase(name)) {
                return sortDirection;
            }
        }
        throw new IllegalArgumentException("No sort direction found for name " + name);
    }

    public String toString() {
        return name;
    }

}
