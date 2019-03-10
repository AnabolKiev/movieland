package com.anabol.movieland.dao.jdbc.mapper;

import com.anabol.movieland.entity.Movie;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieMapperTest {

    @Test
    public void testMapRow() throws SQLException {
        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getString("nameRussian")).thenReturn("Фильм");
        when(resultSetMock.getString("nameNative")).thenReturn("Movie");
        when(resultSetMock.getString("description")).thenReturn("Фильм о том, как писать тесты");
        when(resultSetMock.getString("yearOfRelease")).thenReturn("2019");
        when(resultSetMock.getDouble("rating")).thenReturn(7.99);
        when(resultSetMock.getDouble("price")).thenReturn(99.99);
        when(resultSetMock.getString("picturePath")).thenReturn("http://images.com/1.jpg");

        MovieMapper movieMapper = new MovieMapper();
        Movie movie = movieMapper.mapRow(resultSetMock, 0);

        assertEquals(1, movie.getId());
        assertEquals("Фильм", movie.getNameRussian());
        assertEquals("Movie", movie.getNameNative());
        assertEquals("Фильм о том, как писать тесты", movie.getDescription());
        assertEquals("2019", movie.getYearOfRelease());
        assertEquals(7.99, movie.getRating(), 0.001);
        assertEquals(99.99, movie.getPrice(), 0.001);
        assertEquals("http://images.com/1.jpg", movie.getPicturePath());
    }


}
