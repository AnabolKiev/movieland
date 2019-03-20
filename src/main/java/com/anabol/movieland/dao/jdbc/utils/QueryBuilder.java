package com.anabol.movieland.dao.jdbc.utils;

import com.anabol.movieland.web.utils.RequestParameters;

public class QueryBuilder {
    public static String addOrder(String basicQuery, RequestParameters requestParameters) {
        return basicQuery + " ORDER BY " + requestParameters.getAttribute() + " " + requestParameters.getSortDirection();
    }
}
