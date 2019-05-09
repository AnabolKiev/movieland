package com.anabol.movieland.config;

import com.anabol.movieland.dao.CurrencyDao;
import com.anabol.movieland.service.SecurityService;
import com.anabol.movieland.web.utils.Currency;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.when;

@Configuration
@Profile("testMocks")
public class TestContext {

    @Bean
    public SecurityService securityService() {
        return Mockito.mock(SecurityService.class);
    }

    @Bean
    public CurrencyDao currencyDao() {
        CurrencyDao currencyDao = Mockito.mock(CurrencyDao.class);
        when(currencyDao.getRate(Currency.UAH)).thenReturn(1.00);
        when(currencyDao.getRate(Currency.USD)).thenReturn(27.15);
        return currencyDao;
    }

}
