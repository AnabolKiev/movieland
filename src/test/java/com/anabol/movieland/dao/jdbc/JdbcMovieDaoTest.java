package com.anabol.movieland.dao.jdbc;

import com.anabol.movieland.web.utils.RequestParameters;
import com.anabol.movieland.web.utils.SortDirection;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JdbcMovieDaoTest {
    @Test
    public void testAddOrder() {
        RequestParameters requestParameters = new RequestParameters("column1", SortDirection.DESC);
        String query = JdbcMovieDao.addOrder("SELECT * FROM table", requestParameters);
        assertEquals("SELECT * FROM table ORDER BY column1 DESC", query);
    }

}
