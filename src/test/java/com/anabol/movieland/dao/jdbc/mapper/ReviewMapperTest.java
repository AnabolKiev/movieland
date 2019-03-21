package com.anabol.movieland.dao.jdbc.mapper;

import com.anabol.movieland.entity.Review;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewMapperTest {
    @Test
    public void testMapRow() throws SQLException {
        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getInt("userId")).thenReturn(1);
        when(resultSetMock.getString("nickname")).thenReturn("John Smith");
        when(resultSetMock.getString("text")).thenReturn("Awesome movie");

        ReviewMapper reviewMapper = new ReviewMapper();
        Review review = reviewMapper.mapRow(resultSetMock, 0);

        assertEquals(1, review.getId());
        assertEquals(1, review.getUser().getId());
        assertEquals("John Smith", review.getUser().getNickName());
        assertEquals("Awesome movie", review.getText());
    }
}
