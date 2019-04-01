package com.anabol.movieland.service;

import com.anabol.movieland.entity.Movie;
import com.anabol.movieland.web.utils.Currency;
import com.anabol.movieland.web.utils.RequestParameters;

public interface CurrencyService {
    double convert(double amount, Currency currencyFrom, Currency currencyTo);
    void convert(Movie movie, RequestParameters requestParameters);
}
