package com.anabol.movieland.dao.jdbc.utils;

import com.anabol.movieland.web.utils.RequestParameters;
import com.anabol.movieland.web.utils.SortDirection;
import org.junit.Test;

import static com.anabol.movieland.dao.jdbc.utils.QueryBuilder.addOrder;
import static org.junit.Assert.assertEquals;

public class QueryBuilderTest {
    @Test
    public void testAddOrder() {
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.setAttribute("column1");
        requestParameters.setSortDirection(SortDirection.DESC);
        String query = addOrder("SELECT * FROM table", requestParameters);
        assertEquals("SELECT * FROM table ORDER BY column1 DESC", query);
    }

}
