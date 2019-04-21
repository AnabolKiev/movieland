package com.anabol.movieland.dao.cached;

import com.anabol.movieland.dao.jdbc.JdbcCountryDao;
import com.anabol.movieland.entity.Country;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CachedCountryDaoTest {

    @Test
    public void testGetAll() {
        JdbcCountryDao jdbcCountryDaoMock = mock(JdbcCountryDao.class);
        Country firstCountry = new Country(1, "США");
        Country secondCountry = new Country(2, "Франция");
        when(jdbcCountryDaoMock.getAll()).thenReturn(Arrays.asList(firstCountry, secondCountry));

        CachedCountryDao cachedCountryDao = new CachedCountryDao(jdbcCountryDaoMock);
        cachedCountryDao.refreshCache();
        cachedCountryDao.getAll();
        cachedCountryDao.getAll();

        verify(jdbcCountryDaoMock, times(1)).getAll();
        verifyNoMoreInteractions(jdbcCountryDaoMock);
    }

}
