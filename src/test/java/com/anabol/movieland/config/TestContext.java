package com.anabol.movieland.config;

import com.anabol.movieland.dao.CurrencyDao;
import com.anabol.movieland.service.SecurityService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("testMocks")
public class TestContext {

    @Bean
    public SecurityService securityService() {
        return Mockito.mock(SecurityService.class);
    }

    @Bean
    public CurrencyDao currencyDao() {
        return Mockito.mock(CurrencyDao.class);
    }

}
