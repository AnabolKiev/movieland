package com.anabol.movieland.service;

import com.anabol.movieland.web.utils.Currency;

public interface CurrencyService {
    double convert(double amount, Currency currency);
}
