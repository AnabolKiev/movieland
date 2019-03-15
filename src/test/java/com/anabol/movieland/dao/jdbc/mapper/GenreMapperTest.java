package com.anabol.movieland.dao.jdbc.mapper;

import com.anabol.movieland.entity.Genre;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenreMapperTest {
    @Test
    public void testMapRow() throws SQLException {
        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getString("name")).thenReturn("драма");

        GenreMapper genreMapper = new GenreMapper();
        Genre genre = genreMapper.mapRow(resultSetMock, 0);

        assertEquals(1, genre.getId());
        assertEquals("драма", genre.getName());
    }
}
