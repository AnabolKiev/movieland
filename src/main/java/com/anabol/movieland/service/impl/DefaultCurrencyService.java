package com.anabol.movieland.service.impl;

import com.anabol.movieland.dao.CurrencyDao;
import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.service.CurrencyService;
import com.anabol.movieland.web.utils.Currency;
import com.anabol.movieland.web.utils.RequestParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultCurrencyService implements CurrencyService {
    private final CurrencyDao currencyDao;

    @Override
    public double convert(double amount, Currency currencyFrom, Currency currencyTo) {
        return amount * currencyDao.getRate(currencyFrom) / currencyDao.getRate(currencyTo);
    }

    @Override
    public void convert(Movie movie, RequestParameters requestParameters) {
        double price = convert(movie.getPrice(), Currency.UAH, requestParameters.getCurrency());
        movie.setPrice(price);
    }

}
