package com.anabol.movieland.web.controller;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml", "file:src/main/webapp/WEB-INF/action-servlet.xml"})
@WebAppConfiguration
public class MovieControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private MovieService movieServiceMock;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(movieServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Movie firstMovie = new Movie();
        firstMovie.setId(1);
        firstMovie.setNameRussian("Первый фильм");
        firstMovie.setNameNative("First movie");
        firstMovie.setDescription("Some description");
        firstMovie.setYearOfRelease("2019");
        firstMovie.setRating(8.05);
        firstMovie.setPrice(123.45);
        firstMovie.setPicturePath("http://images.com/1.jpg");

        Movie secondMovie = new Movie();
        secondMovie.setId(2);
        secondMovie.setNameRussian("Второй фильм");
        secondMovie.setNameNative("Second movie");
        secondMovie.setDescription("Some description");
        secondMovie.setYearOfRelease("2000");
        secondMovie.setRating(7.99);
        secondMovie.setPrice(111.22);
        secondMovie.setPicturePath("http://images.com/2.jpg");

        List<Movie> movies = Arrays.asList(firstMovie, secondMovie);
        when(movieServiceMock.getAll()).thenReturn(movies);
        when(movieServiceMock.getRandom()).thenReturn(movies);
        when(movieServiceMock.getByGenreId(1)).thenReturn(movies);
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nameRussian", is("Первый фильм")))
                .andExpect(jsonPath("$[0].nameNative", is("First movie")))
                .andExpect(jsonPath("$[0].description").doesNotExist())
                .andExpect(jsonPath("$[0].yearOfRelease", is("2019")))
                .andExpect(jsonPath("$[0].rating", is(8.05)))
                .andExpect(jsonPath("$[0].price", is(123.45)))
                .andExpect(jsonPath("$[0].picturePath", is("http://images.com/1.jpg")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].nameRussian", is("Второй фильм")))
                .andExpect(jsonPath("$[1].nameNative", is("Second movie")))
                .andExpect(jsonPath("$[1].description").doesNotExist())
                .andExpect(jsonPath("$[1].yearOfRelease", is("2000")))
                .andExpect(jsonPath("$[1].rating", is(7.99)))
                .andExpect(jsonPath("$[1].price", is(111.22)))
                .andExpect(jsonPath("$[1].picturePath", is("http://images.com/2.jpg")));

        verify(movieServiceMock, times(1)).getAll(); // Verify that the getAll() method of the MovieService interface is called only once
        verifyNoMoreInteractions(movieServiceMock);  // Ensure that no other methods of our mock object are called during the test
    }

    @Test
    public void testGetRandom() throws Exception {
        mockMvc.perform(get("/movie/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].description").doesNotExist())
                .andExpect(jsonPath("$[1].description").doesNotExist());

        verify(movieServiceMock, times(1)).getRandom();
        verifyNoMoreInteractions(movieServiceMock);
    }

    @Test
    public void testGetByGenre() throws Exception {
        mockMvc.perform(get("/movie/genre/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].description").doesNotExist())
                .andExpect(jsonPath("$[1].description").doesNotExist());

        verify(movieServiceMock, times(1)).getByGenreId(1);
        verifyNoMoreInteractions(movieServiceMock);
    }
}
