package com.anabol.movieland.config;

import com.anabol.movieland.service.MovieService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("movieServiceMock")
public class TestMovieServiceContext {

    @Bean
    public MovieService movieService() {
        return Mockito.mock(MovieService.class);
    }

}
