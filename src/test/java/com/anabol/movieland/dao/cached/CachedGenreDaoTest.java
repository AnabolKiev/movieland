package com.anabol.movieland.dao.cached;

import com.anabol.movieland.dao.jdbc.JdbcGenreDao;
import com.anabol.movieland.entity.Genre;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class CachedGenreDaoTest {
    @Test
    public void testGetAll() throws Exception {
        JdbcGenreDao jdbcGenreDaoMock = mock(JdbcGenreDao.class);

        Genre firstGenre = new Genre();
        firstGenre.setId(1);
        firstGenre.setName("драма");

        Genre secondGenre = new Genre();
        secondGenre.setId(2);
        secondGenre.setName("криминал");

        when(jdbcGenreDaoMock.getAll()).thenReturn(Arrays.asList(firstGenre, secondGenre));

        CachedGenreDao cachedGenreDao = new CachedGenreDao(jdbcGenreDaoMock);
        cachedGenreDao.getAll();
        cachedGenreDao.getAll();

        verify(jdbcGenreDaoMock, times(1)).getAll();
        verifyNoMoreInteractions(jdbcGenreDaoMock);
    }
}
