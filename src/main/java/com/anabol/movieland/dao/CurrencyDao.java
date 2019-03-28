package com.anabol.movieland.dao;

import com.anabol.movieland.web.utils.Currency;

public interface CurrencyDao {
    double getRate(Currency currency);
}
